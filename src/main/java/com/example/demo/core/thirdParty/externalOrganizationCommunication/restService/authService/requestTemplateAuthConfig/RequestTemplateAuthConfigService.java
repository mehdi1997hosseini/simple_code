package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestTemplateAuthConfig;

import com.example.demo.core.service.BasicService;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestTemplateAuthConfig.dto.RequestTemplateAuthConfigDto;

public interface RequestTemplateAuthConfigService extends BasicService<RequestTemplateAuthConfigEntity, String> {

    RequestTemplateAuthConfigEntity save(RequestTemplateAuthConfigDto dto);

}
