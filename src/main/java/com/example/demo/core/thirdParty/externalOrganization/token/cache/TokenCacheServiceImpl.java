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
            ExternalTokenDto tokenDto = tokenMap.get(orgName);
            if (tokenInfo.getCountTry() != null && tokenInfo.getCountTry() != 0)
                tokenDto.setCountTry(tokenInfo.getCountTry());

            if (tokenDto.getIsValidToken() && !tokenInfo.getIsValidToken() && tokenInfo.getCountTry() > 3) {
                tokenDto.setIsValidToken(false);
                tokenDto.setToken(tokenInfo.getToken());
                tokenDto.setExpiresAt(tokenInfo.getExpiresAt());
            } else if (!tokenDto.getIsValidToken() && tokenInfo.getIsValidToken()) {
                tokenDto.setIsValidToken(true);
                tokenDto.setToken(tokenInfo.getToken());
                tokenDto.setExpiresAt(tokenInfo.getExpiresAt());
                tokenDto.setCountTry(tokenInfo.getCountTry());
            } else if (tokenInfo.getIsValidToken() && tokenInfo.getToken() != null && tokenInfo.getExpiresAt() != null) {
                tokenDto.setIsValidToken(true);
                tokenDto.setToken(tokenInfo.getToken());
                tokenDto.setExpiresAt(tokenInfo.getExpiresAt());
                tokenDto.setCountTry(tokenInfo.getCountTry());
            }

            tokenMap.replace(orgName, tokenDto);
        }
        // add new token for organization name
        else {
            ExternalTokenDto newTokenInfo = new ExternalTokenDto(tokenInfo.getToken(), tokenInfo.getExpiresAt(), tokenInfo.getIsValidToken(), tokenInfo.getCountTry() == null ? 0 : tokenInfo.getCountTry());
            tokenMap.put(orgName, newTokenInfo);
        }
    }

    public ExternalTokenDto getToken(ExternalOrganizationName orgName) {
        if (tokenMap.isEmpty())
            return null;
        if (tokenMap.containsKey(orgName)) {
            return tokenMap.get(orgName);
        }
        return null;
    }

    public void clearDataTokens() {
        tokenMap.clear();
    }

    @Override
    public Map<ExternalOrganizationName, ExternalTokenDto> getAll() {
        return tokenMap;
    }


}
