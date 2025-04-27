package com.example.demo.core.thirdParty.externalOrganization.token.tokenStrategy;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;

import java.util.HashMap;
import java.util.Map;

class SamlStrategy implements TokenStrategy {
    @Override
    public Map<String, String> prepareAuthParams(ExternalOrganizationEntity org) {
        Map<String, String> samlRequest = new HashMap<>();
        samlRequest.put(org.getRequestTemplate().getSamlRequestXmlParamName(), org.getRequestTokenConfig().getSamlRequestXml());
        return samlRequest;
    }
}
