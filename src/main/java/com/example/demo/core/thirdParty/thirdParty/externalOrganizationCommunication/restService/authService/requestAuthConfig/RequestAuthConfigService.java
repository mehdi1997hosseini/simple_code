package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.authService.requestAuthConfig;

import ir.smarttrustco.pssnote.core.service.BasicService;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.requestAuthConfig.dto.RequestAuthConfigDto;

public interface RequestAuthConfigService extends BasicService<RequestAuthConfigEntity, String> {
    RequestAuthConfigEntity save(RequestAuthConfigDto dto);
}
