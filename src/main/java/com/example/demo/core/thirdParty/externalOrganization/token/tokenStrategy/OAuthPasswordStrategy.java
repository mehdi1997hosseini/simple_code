package com.example.demo.core.thirdParty.externalOrganization.token.tokenStrategy;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;

import java.util.HashMap;
import java.util.Map;

class OAuthPasswordStrategy implements TokenStrategy {
    @Override
    public Map<String, String> prepareAuthParams(ExternalOrganizationEntity org) {
        Map<String, String> body = new HashMap<>();
        body.put("username", org.getUsername());
        body.put("password", org.getPassword());
        body.put("client_id", org.getClientId());
        body.put("client_secret", org.getClientSecret());
        body.put("grant_type", "password");
        return body;
    }
}
