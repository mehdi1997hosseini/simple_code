package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.authService.requestTemplateAuthConfig;

import ir.smarttrustco.pssnote.core.service.BasicService;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.requestTemplateAuthConfig.dto.RequestTemplateAuthConfigDto;

public interface RequestTemplateAuthConfigService extends BasicService<RequestTemplateAuthConfigEntity,String> {

    RequestTemplateAuthConfigEntity save(RequestTemplateAuthConfigDto dto);

}
