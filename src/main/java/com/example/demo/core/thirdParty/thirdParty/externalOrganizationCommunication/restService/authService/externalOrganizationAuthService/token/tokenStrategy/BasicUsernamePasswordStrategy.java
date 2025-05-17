package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.tokenStrategy;


import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;

import java.util.HashMap;
import java.util.Map;

class BasicUsernamePasswordStrategy implements TokenStrategy {
    @Override
    public Map<String, String> prepareAuthParams(ExternalOrganizationAuthServiceEntity org) {
        Map<String, String> params = new HashMap<>();
        params.put(org.getRequestTemplate().getUsernameParamName(), org.getRequestTokenConfig().getUsername());
        params.put(org.getRequestTemplate().getPasswordParamName(), org.getRequestTokenConfig().getPassword());
        return params;
    }
}
