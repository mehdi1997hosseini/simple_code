package com.example.demo.core.thirdParty.externalOrganization.token.tokenStrategy;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;

import java.util.HashMap;
import java.util.Map;

class ClientCredentialsStrategy implements TokenStrategy {

    @Override
    public Map<String, String> prepareAuthParams(ExternalOrganizationEntity org) {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", org.getClientId());
        params.put("client_secret", org.getClientSecret());
        params.put("grant_type", "client_credentials");
        return params;
    }
}
