package com.example.demo.core.thirdParty.externalOrganization.token.cache;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;
import com.example.demo.core.thirdParty.externalOrganization.token.ExternalTokenDto;

public interface TokenCacheService {
    public void saveOrUpdateToken(ExternalOrganizationName orgId, ExternalTokenDto tokenInfo);
    public ExternalTokenDto getToken(ExternalOrganizationName orgName);
    public void clearDataTokens();

}
