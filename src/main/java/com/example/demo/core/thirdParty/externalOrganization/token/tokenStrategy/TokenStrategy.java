package com.example.demo.core.thirdParty.externalOrganization.token.tokenStrategy;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import com.example.demo.core.thirdParty.externalOrganization.token.ExternalTokenDto;

import java.util.Map;

public interface TokenStrategy {
    Map<String, String> prepareAuthParams(ExternalOrganizationEntity org);
}
