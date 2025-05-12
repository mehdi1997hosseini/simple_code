package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.tokenStrategy;


import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;

import java.util.Map;

public interface TokenStrategy {
    Map<String, String> prepareAuthParams(ExternalOrganizationAuthServiceEntity org);
}
