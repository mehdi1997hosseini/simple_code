package com.example.demo.core.profile;

import com.example.demo.core.config.LoggingConfig;
import com.example.demo.core.logger.httpRequestLog.HttpRequestLogService;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationService;
import com.example.demo.core.thirdParty.externalOrganization.cache.ExternalOrganizationCatchService;
import com.example.demo.core.thirdParty.externalOrganization.token.TokenSchedulerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile("mysql")
public class MySqlProServiceImpl extends LoggingConfig implements ProfilesService, CommandLineRunner/*, Filter */ {

    private final ExternalOrganizationService externalOrganizationService;
    private final ExternalOrganizationCatchService externalOrganizationCatchService;
    private final TokenSchedulerService tokenSchedulerService;

    public MySqlProServiceImpl(ExternalOrganizationService externalOrganizationService, ExternalOrganizationCatchService externalOrganizationCatchService,
                               TokenSchedulerService tokenSchedulerService, HttpRequestLogService httpRequestLogService) {
        super(httpRequestLogService);
        this.externalOrganizationService = externalOrganizationService;
        this.externalOrganizationCatchService = externalOrganizationCatchService;
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
        externalOrganizationCatchService.refreshAllCatch(externalOrganizationService.findAllInCache());
        Optional.ofNullable(externalOrganizationCatchService.findAllExternalOrganization())
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