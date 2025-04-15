package com.example.demo.core.thirdParty.externalOrganization.token;

import com.example.demo.core.thirdParty.externalOrganization.dto.ExternalOrganizationDto;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

public class TokenSchedulerService {
    private final TaskScheduler scheduler = new ConcurrentTaskScheduler();
    private final TokenCache tokenCache;
    private final TokenService tokenService;

    public TokenSchedulerService(TokenCache tokenCache, TokenService tokenService) {
        this.tokenCache = tokenCache;
        this.tokenService = tokenService;
    }


    public void scheduleTokenRefresh(ExternalOrganizationDto org) {
//        ExternalTokenDto tokenInfo = tokenCache.getToken();
//        if (tokenInfo == null) return;
//
//        Instant refreshTime = tokenInfo.getExpiresAt();
//
//        scheduler.schedule(() -> {
//            ExternalTokenInfo newToken = tokenService.fetchToken(org);
//            tokenCache.saveToken(org.getOrgId(), newToken);
//            scheduleTokenRefresh(org); // بازنشانی زمانبندی جدید
//        }, Date.from(refreshTime));
    }

}
