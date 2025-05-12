package com.example.demo.core.profile;

import com.example.demo.core.config.LoggingConfig;
import com.example.demo.core.logger.httpRequestLog.HttpRequestLogService;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthService;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.TokenSchedulerService;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.cache.authConfigCache.ExternalOrganizationAuthCatchService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile("mysql")
public class MySqlProServiceImpl extends LoggingConfig implements ProfilesService, CommandLineRunner/*, Filter */ {

    private final ExternalOrganizationAuthService externalOrganizationAuthService;
    private final ExternalOrganizationAuthCatchService externalOrganizationAuthCatchService;
    private final TokenSchedulerService tokenSchedulerService;

    public MySqlProServiceImpl(ExternalOrganizationAuthService externalOrganizationAuthService,
                               ExternalOrganizationAuthCatchService externalOrganizationAuthCatchService,
                               TokenSchedulerService tokenSchedulerService,
                               HttpRequestLogService httpRequestLogService) {
        super(httpRequestLogService);
        this.externalOrganizationAuthService = externalOrganizationAuthService;
        this.externalOrganizationAuthCatchService = externalOrganizationAuthCatchService;
        this.tokenSchedulerService = tokenSchedulerService;
    }

    @Override
    public String getProfileName() {
        return "profile By Database MySql ....";
    }

    @Override
    public void run(String... args) throws Exception {
        tokenProcess();
    }

    private void tokenProcess() {
        externalOrganizationAuthCatchService.refreshAllCatch(externalOrganizationAuthService.findAllInCacheWhenStartApp());
        Optional.ofNullable(externalOrganizationAuthCatchService.findAllExternalOrganization())
                .filter(map -> !map.isEmpty())
                .ifPresent(tokenSchedulerService::initTokens);
    }

    //    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        Long requestNumber = NumberUtils.uniqueNumber();
//        MDC.put("requestId", String.valueOf(requestNumber));  // اضافه کردن شناسه به MDC
//
//        logger.info("Request received with ID: {}", requestNumber);
//
//        filterChain.doFilter(servletRequest, servletResponse);
//        MDC.remove("requestId");
//
//    }
}