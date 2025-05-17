package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.tokenStrategy;


import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;

import java.util.Map;

public interface TokenStrategy {
    Map<String, String> prepareAuthParams(ExternalOrganizationAuthServiceEntity org);
}
