package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestAuthConfig;

import com.example.demo.core.service.BasicService;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestAuthConfig.dto.RequestAuthConfigDto;

public interface RequestAuthConfigService extends BasicService<RequestAuthConfigEntity, String> {
    RequestAuthConfigEntity save(RequestAuthConfigDto dto);
}
