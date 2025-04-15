package com.example.demo.core.thirdParty.externalOrganization.token;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenCache {
    private final Map<ExternalOrganizationName, ExternalTokenDto> tokenMap = new ConcurrentHashMap<>();

    public void saveToken(ExternalOrganizationName orgId, ExternalTokenDto tokenInfo) {
        tokenMap.put(orgId, tokenInfo);
    }

    public ExternalTokenDto getToken(ExternalOrganizationName orgName) {
        return tokenMap.get(orgName);
    }

}
