package com.example.demo.core.thirdParty.externalOrganization.token;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;
import com.example.demo.core.thirdParty.externalOrganization.token.cache.TokenCacheService;
import com.example.demo.core.thirdParty.externalOrganization.token.cache.TokenCacheServiceImpl;
import com.example.demo.core.thirdParty.externalOrganization.token.service.TokenService;
import com.example.demo.core.thirdParty.externalOrganization.token.service.TokenServiceImpl;
import com.example.demo.core.utility.DateTimeZoneUtil;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.*;

@Component
public class TokenSchedulerService {
//    private final TaskScheduler scheduler = new ConcurrentTaskScheduler();

    private final Map<ExternalOrganizationName, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(20); // به تعداد سازمان‌ها یا dynamic

    private final TokenCacheService tokenCacheService;
    private final TokenService tokenService;

    public TokenSchedulerService(TokenCacheServiceImpl tokenCacheService, TokenServiceImpl tokenService) {
        this.tokenCacheService = tokenCacheService;
        this.tokenService = tokenService;
    }

    public void initTokens(Map<ExternalOrganizationName, ExternalOrganizationEntity> externalOrganizationEntityMap) {
        externalOrganizationEntityMap.forEach(this::scheduleRefresh);
    }

    private void scheduleRefresh(ExternalOrganizationName extOrgName, ExternalOrganizationEntity extOrgEntity) {
        try {
            ExternalTokenDto token = tokenService.fetchTokenByRest(extOrgEntity);
            tokenCacheService.saveOrUpdateToken(extOrgName, token);

            Duration durationFromExpiresIn = DateTimeZoneUtil.DurationAndInstantUtils.toDurationFromInstant(token.getExpiresAt());

            Runnable task = () -> refreshToken(extOrgName, extOrgEntity);
            ScheduledFuture<?> future = scheduler.schedule(task, durationFromExpiresIn.toMillis(), TimeUnit.MILLISECONDS);
            scheduledTasks.put(extOrgName, future);

        } catch (Exception ex) {
            ex.printStackTrace();
            // اگر سرور مقصد از دسترس خارج بود
            retryLater(extOrgName, extOrgEntity);
        }
    }

    private void refreshToken(ExternalOrganizationName extOrgName, ExternalOrganizationEntity org) {
        try {
            ExternalTokenDto newToken = tokenService.fetchTokenByRest(org);
            tokenCacheService.saveOrUpdateToken(org.getOrgName(), newToken);
            scheduleRefresh(extOrgName, org); // بازتنظیم برنامه‌ریزی برای زمان بعدی
        } catch (Exception ex) {
            ex.printStackTrace();
            retryLater(extOrgName, org); // اگر نشد، دوباره بعداً امتحان کن
        }
    }

    private void retryLater(ExternalOrganizationName extOrgName, ExternalOrganizationEntity org) {
        ExternalTokenDto token = tokenCacheService.getToken(extOrgName);
        if (token != null) {
            if (token.getCountTry() < 3) {
                token.setCountTry(token.getCountTry() + 1);
                scheduler.schedule(() -> scheduleRefresh(extOrgName, org), 1, TimeUnit.MINUTES);
            } else if (token.getCountTry() == 3) {
                token.setCountTry(token.getCountTry() + 1);
                scheduler.schedule(() -> scheduleRefresh(extOrgName, org), 24, TimeUnit.HOURS);
            } else {
                token.setValidToken(false);
                token.setCountTry(token.getCountTry() + 1);
                ScheduledFuture<?> remove = scheduledTasks.remove(extOrgName);
                if (remove != null) {
                    remove.cancel(true);
                }
            }
            tokenCacheService.saveOrUpdateToken(extOrgName, token);
        }
    }

    public void resetTokenManually(ExternalOrganizationName extOrgName, ExternalOrganizationEntity extOrgEntity) {
        ExternalTokenDto token = tokenCacheService.getToken(extOrgName);
        if (token != null) {
            token.setCountTry(0);
            token.setValidToken(true);
            tokenCacheService.saveOrUpdateToken(extOrgName, token);
            scheduleRefresh(extOrgName, extOrgEntity);
//            System.out.println("توکن برای " + extOrgName + " ریست شد و برنامه‌ریزی مجدد انجام شد.");
        }
//        else {
//            System.out.println("توکنی برای " + extOrgName + " وجود ندارد.");
//        }
    }

//    public void scheduleTokenRefreshForRest(ExternalOrganizationName extOrgName, ExternalOrganizationEntity extOrgEntity) {
//        ExternalTokenDto tokenInfo = tokenCacheService.getToken(extOrgName);
//        if (tokenInfo != null) {
//            Instant refreshTime = tokenInfo.getExpiresAt();
//
//            scheduler.schedule(() -> {
//                ExternalTokenDto newToken = tokenService.fetchTokenByRest(extOrgEntity);
//                System.out.println("refreshTime: +++++ " + newToken.getToken() + "\t\t" + newToken.getExpiresAt() + "  ++++++======in time===>" + LocalDateTime.now());
//                tokenCacheService.saveOrUpdateToken(extOrgName, newToken);
//                scheduleTokenRefreshForRest(extOrgName, extOrgEntity); // بازنشانی زمانبندی جدید
//            }, Date.from(refreshTime));
//        } else {
//
//            ExternalTokenDto newToken = tokenService.fetchTokenByRest(extOrgEntity);
//            scheduler.schedule(() -> {
//                System.out.println("refreshTime: " + newToken.getToken() + "\t\t" + newToken.getExpiresAt() + "  ======in time===>" + LocalDateTime.now());
//                tokenCacheService.saveOrUpdateToken(extOrgName, newToken);
//                scheduleTokenRefreshForRest(extOrgName, extOrgEntity); // بازنشانی زمانبندی جدید
//            }, Date.from(newToken.getExpiresAt()));
//        }
//    }

}
