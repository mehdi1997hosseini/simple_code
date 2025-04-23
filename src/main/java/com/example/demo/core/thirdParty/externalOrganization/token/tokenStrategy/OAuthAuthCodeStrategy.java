package com.example.demo.core.thirdParty.externalOrganization.token.tokenStrategy;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;

import java.util.HashMap;
import java.util.Map;

class OAuthAuthCodeStrategy implements TokenStrategy {
    @Override
    public Map<String, String> prepareAuthParams(ExternalOrganizationEntity org) {
        Map<String, String> body = new HashMap<>();
        body.put("code", org.getAuthCode());
        body.put("client_id", org.getClientId());
        body.put("client_secret", org.getClientSecret());
        body.put("redirect_uri", org.getRedirectUri());
        body.put("grant_type", "authorization_code");
        return body;
    }
}
