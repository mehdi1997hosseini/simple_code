package com.example.demo.core.thirdParty.externalOrganization.token.tokenStrategy;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;

import java.util.HashMap;
import java.util.Map;

class BasicUsernamePasswordStrategy implements TokenStrategy {
    @Override
    public Map<String, String> prepareAuthParams(ExternalOrganizationEntity org) {
        Map<String, String> params = new HashMap<>();
        params.put("username", org.getUsername());
        params.put("password", org.getPassword());
        return params;
    }
}
