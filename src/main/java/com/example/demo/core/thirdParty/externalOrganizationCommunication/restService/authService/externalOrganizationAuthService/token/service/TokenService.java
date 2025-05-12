package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.service;

import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.ExternalTokenDto;
import org.springframework.http.HttpMethod;

public interface TokenService {
    public ExternalTokenDto fetchTokenByRest(ExternalOrganizationAuthServiceEntity extOrgEntity);

    public ExternalTokenDto fetchTokenByRest(ExternalOrganizationAuthServiceEntity extOrgEntity, HttpMethod method);

    public ExternalTokenDto fetchTokenBySoap(ExternalOrganizationAuthServiceEntity extOrgEntity);
}
