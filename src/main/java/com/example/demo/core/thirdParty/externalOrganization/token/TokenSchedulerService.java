package com.example.demo.core.thirdParty.externalOrganization.token;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;
import com.example.demo.core.thirdParty.externalOrganization.token.cache.TokenCacheService;
import com.example.demo.core.thirdParty.externalOrganization.token.cache.TokenCacheServiceImpl;
import com.example.demo.core.thirdParty.externalOrganization.token.service.TokenService;
import com.example.demo.core.thirdParty.externalOrganization.token.service.TokenServiceImpl;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
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

    public void initTokens(Map<ExternalOrganizationName , ExternalOrganizationEntity> externalOrganizationEntityMap) {
        externalOrganizationEntityMap.forEach(this::scheduleRefresh);
    }

    private void scheduleRefresh(ExternalOrganizationName extOrgName, ExternalOrganizationEntity extOrgEntity) {
        int count = 0;
        try {
            count++;
            System.out.println("Refreshing external organization " + extOrgName + " with " + count + " tokens");
            ExternalTokenDto token = tokenService.fetchTokenByRest(extOrgEntity);
            tokenCacheService.saveOrUpdateToken(extOrgName, token);

            long delayMillis = Duration.between(Instant.now(), token.getExpiresAt())
                    .minusSeconds(60) // مثلاً یک دقیقه قبل انقضا
                    .toMillis();

            Runnable task = () -> refreshToken(extOrgName, extOrgEntity);
            ScheduledFuture<?> future = scheduler.schedule(task, delayMillis, TimeUnit.MILLISECONDS);
            scheduledTasks.put(extOrgName, future);

        } catch (Exception ex) {
            ex.printStackTrace();
            // اگر سرور مقصد از دسترس خارج بود
            retryLater(extOrgName, extOrgEntity); // متد بعدی رو نگاه کن
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
        scheduler.schedule(() -> scheduleRefresh(extOrgName, org), 5, TimeUnit.MINUTES);
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
