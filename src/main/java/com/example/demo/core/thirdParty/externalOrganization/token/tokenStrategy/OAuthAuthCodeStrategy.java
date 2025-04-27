package com.example.demo.core.thirdParty.externalOrganization.token.tokenStrategy;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;

import java.util.HashMap;
import java.util.Map;

class OAuthAuthCodeStrategy implements TokenStrategy {
    @Override
    public Map<String, String> prepareAuthParams(ExternalOrganizationEntity org) {
        Map<String, String> body = new HashMap<>();
        body.put(org.getRequestTemplate().getAuthCodeParamName(), org.getRequestTokenConfig().getAuthCode());
        body.put(org.getRequestTemplate().getClientIdParamName(), org.getRequestTokenConfig().getClientId());
        body.put(org.getRequestTemplate().getClientSecretParamName(), org.getRequestTokenConfig().getClientSecret());
        body.put(org.getRequestTemplate().getRedirectUriParamName(), org.getRequestTokenConfig().getRedirectUri());
        body.put(org.getRequestTemplate().getGrantTypeParamName(), "authorization_code");
        return body;
    }
}
