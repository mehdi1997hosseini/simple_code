package com.example.demo.core.thirdParty.externalOrganization.token.tokenStrategy;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;

import java.util.Map;

class JwtStrategy implements TokenStrategy {
    @Override
    public Map<String, String> prepareAuthParams(ExternalOrganizationEntity org) {
        return Map.of();
    }
}
