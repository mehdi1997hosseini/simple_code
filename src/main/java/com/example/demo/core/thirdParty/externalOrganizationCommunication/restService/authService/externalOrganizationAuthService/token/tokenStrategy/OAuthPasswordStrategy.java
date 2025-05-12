package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.tokenStrategy;


import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;

import java.util.HashMap;
import java.util.Map;

class OAuthPasswordStrategy implements TokenStrategy {
    @Override
    public Map<String, String> prepareAuthParams(ExternalOrganizationAuthServiceEntity org) {
        Map<String, String> body = new HashMap<>();
        body.put(org.getRequestTemplate().getUsernameParamName(), org.getRequestTokenConfig().getUsername());
        body.put(org.getRequestTemplate().getPasswordParamName(), org.getRequestTokenConfig().getPassword());
        body.put(org.getRequestTemplate().getClientIdParamName(), org.getRequestTokenConfig().getClientId());
        body.put(org.getRequestTemplate().getClientSecretParamName(), org.getRequestTokenConfig().getClientSecret());
        body.put(org.getRequestTemplate().getGrantTypeParamName(), "password");
        return body;
    }
}
