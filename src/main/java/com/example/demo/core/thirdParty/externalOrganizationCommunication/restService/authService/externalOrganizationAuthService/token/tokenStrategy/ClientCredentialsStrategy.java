package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.tokenStrategy;


import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;

import java.util.HashMap;
import java.util.Map;

class ClientCredentialsStrategy implements TokenStrategy {

    @Override
    public Map<String, String> prepareAuthParams(ExternalOrganizationAuthServiceEntity org) {
        Map<String, String> params = new HashMap<>();
        params.put(org.getRequestTemplate().getClientIdParamName(), org.getRequestTokenConfig().getClientId());
        params.put(org.getRequestTemplate().getClientSecretParamName(), org.getRequestTokenConfig().getClientSecret());
        params.put(org.getRequestTemplate().getGrantTypeParamName(), "client_credentials");
        return params;
    }

}
