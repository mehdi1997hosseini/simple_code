package com.example.demo.core.thirdParty.externalOrganization.token.cache;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;
import com.example.demo.core.thirdParty.externalOrganization.token.ExternalTokenDto;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenCacheServiceImpl implements TokenCacheService {

    private final Map<ExternalOrganizationName, ExternalTokenDto> tokenMap = new ConcurrentHashMap<>();

    public void saveOrUpdateToken(ExternalOrganizationName orgName, ExternalTokenDto tokenInfo) {
        // update new token for organization name
        if (tokenMap.containsKey(orgName)) {
            ExternalTokenDto updatedTokenInfo = new ExternalTokenDto(tokenInfo.getToken(),tokenInfo.getExpiresAt());
            tokenMap.replace(orgName, updatedTokenInfo);
        }
        // add new token for organization name
        else {
            ExternalTokenDto newTokenInfo = new ExternalTokenDto(tokenInfo.getToken(), tokenInfo.getExpiresAt());
            tokenMap.put(orgName, newTokenInfo);
        }
    }

    public ExternalTokenDto getToken(ExternalOrganizationName orgName) {
        if (tokenMap.isEmpty())
            return null;
        if (tokenMap.containsKey(orgName)){
            return tokenMap.get(orgName);
        }
        return null;
    }

    public void clearDataTokens() {
        tokenMap.clear();
    }


}
