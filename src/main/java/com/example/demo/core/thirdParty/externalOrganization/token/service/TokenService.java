package com.example.demo.core.thirdParty.externalOrganization.token.service;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import com.example.demo.core.thirdParty.externalOrganization.token.ExternalTokenDto;

public interface TokenService {
    public ExternalTokenDto fetchTokenByRest(ExternalOrganizationEntity extOrgEntity);
    public ExternalTokenDto fetchTokenBySoap(ExternalOrganizationEntity extOrgEntity);
}
