package com.example.demo.core.seedData;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationService;
import com.example.demo.core.thirdParty.externalOrganization.cache.ExternalOrganizationCacheService;
import com.example.demo.core.thirdParty.externalOrganization.dto.ExternalOrganizationDto;
import com.example.demo.core.thirdParty.externalOrganization.token.TokenSchedulerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SeedData implements CommandLineRunner {

    private final ExternalOrganizationCacheService externalOrgCacheService;
    private final TokenSchedulerService tokenSchedulerService;

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
