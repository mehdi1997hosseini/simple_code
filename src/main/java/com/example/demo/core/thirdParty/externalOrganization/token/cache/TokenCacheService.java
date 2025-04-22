package com.example.demo.core.thirdParty.externalOrganization.token.cache;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;
import com.example.demo.core.thirdParty.externalOrganization.token.ExternalTokenDto;

import java.util.Map;

public interface TokenCacheService {
    public void saveOrUpdateToken(ExternalOrganizationName orgName, ExternalTokenDto tokenInfo);
    public ExternalTokenDto getToken(ExternalOrganizationName orgName);
    public void clearDataTokens();
    public Map<ExternalOrganizationName, ExternalTokenDto> getAll();
}
