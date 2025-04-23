package com.example.demo.core.thirdParty.externalOrganization.token.service;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import com.example.demo.core.thirdParty.externalOrganization.token.ExternalTokenDto;
import org.springframework.http.HttpMethod;

public interface TokenService {
    public ExternalTokenDto fetchTokenByRest(ExternalOrganizationEntity extOrgEntity);
    public ExternalTokenDto fetchTokenByRest(ExternalOrganizationEntity extOrgEntity , HttpMethod method);
    public ExternalTokenDto fetchTokenBySoap(ExternalOrganizationEntity extOrgEntity);
}
