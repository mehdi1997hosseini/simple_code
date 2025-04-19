package com.example.demo.core.profile;

import com.example.demo.core.thirdParty.externalOrganization.cache.ExternalOrganizationCacheService;
import com.example.demo.core.thirdParty.externalOrganization.token.TokenSchedulerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Profile("mysql")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MySqlProServiceImpl implements ProfilesService, CommandLineRunner {

    private final ExternalOrganizationCacheService externalOrgCacheService;
    private final TokenSchedulerService tokenSchedulerService;


    @Override
    public String getProfileName() {
        return "profile By Database MySql ....";
    }

    @Override
    public void run(String... args) throws Exception {
        tokenProcess();
    }

    private void tokenProcess() {
        externalOrgCacheService.refreshAllExternalOrganization();
        Optional.ofNullable(externalOrgCacheService.getAllExternalOrganization())
                .filter(map -> !map.isEmpty())
                .ifPresent(tokenSchedulerService::initTokens);
    }
}