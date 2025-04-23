package com.example.demo.core.thirdParty.externalOrganization.token;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;
import com.example.demo.core.thirdParty.externalOrganization.token.cache.TokenCacheService;
import com.example.demo.core.thirdParty.externalOrganization.token.service.TokenService;
import com.example.demo.core.thirdParty.externalOrganization.token.service.TokenServiceImpl;
import com.example.demo.core.utility.DateTimeZoneUtil;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import java.util.concurrent.*;

@Component
public class TokenSchedulerService {
//    private final TaskScheduler scheduler = new ConcurrentTaskScheduler();

    private final Map<ExternalOrganizationName, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(20); // به تعداد سازمان‌ها یا dynamic

    private final TokenCacheService tokenCacheService;
    private final TokenService tokenService;

    public TokenSchedulerService(TokenCacheService tokenCacheService, TokenServiceImpl tokenService) {
        this.tokenCacheService = tokenCacheService;
        this.tokenService = tokenService;
    }

    public void initTokens(Map<ExternalOrganizationName, ExternalOrganizationEntity> externalOrganizationEntityMap) {
        externalOrganizationEntityMap.forEach(this::scheduleRefresh);
    }

    private void scheduleRefresh(ExternalOrganizationName extOrgName, ExternalOrganizationEntity extOrgEntity) {
        try {
            ExternalTokenDto token = tokenService.fetchTokenByRest(extOrgEntity, HttpMethod.POST);
            System.out.println("2. token in time " + LocalTime.now() + " , token: " + token.getToken());
            tokenCacheService.saveOrUpdateToken(extOrgName, token);

            Duration durationFromExpiresIn = DateTimeZoneUtil.DurationAndInstantUtils.toDurationFromInstant(token.getExpiresAt());

            Runnable task = () -> refreshToken(extOrgName, extOrgEntity);
            ScheduledFuture<?> future = scheduler.schedule(task, durationFromExpiresIn.toMillis(), TimeUnit.MILLISECONDS);
            scheduledTasks.put(extOrgName, future);

        } catch (Exception ex) {
            System.out.println("2. Error while scheduling refresh token: " + ex.getMessage());
            // اگر سرور مقصد از دسترس خارج بود
            retryLater(extOrgName, extOrgEntity);
        }
    }

    private void refreshToken(ExternalOrganizationName extOrgName, ExternalOrganizationEntity org) {
        try {
            System.out.println("1. org in time " + LocalTime.now() + " , orgName " + extOrgName);
            ExternalTokenDto newToken = tokenService.fetchTokenByRest(org);
            tokenCacheService.saveOrUpdateToken(org.getOrgName(), newToken);
            scheduleRefresh(extOrgName, org); // بازتنظیم برنامه‌ریزی برای زمان بعدی
        } catch (Exception ex) {
            System.out.println("1. Refresh token failed === " + ex.getMessage());
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
                token.setIsValidToken(false);
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
        // بررسی شود که تاریخ انقضا توکن آن گذشته باشد. چون نباید توکن آن معتبر باشد.
        ExternalTokenDto token = tokenCacheService.getToken(extOrgName);
        if (token != null) {
            token.setCountTry(0);
            token.setIsValidToken(true);
            tokenCacheService.saveOrUpdateToken(extOrgName, token);
            scheduleRefresh(extOrgName, extOrgEntity);
//            System.out.println("توکن برای " + extOrgName + " ریست شد و برنامه‌ریزی مجدد انجام شد.");
        } else {
            scheduleRefresh(extOrgName, extOrgEntity);
//            System.out.println("توکنی برای " + extOrgName + " وجود ندارد.");
        }
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
