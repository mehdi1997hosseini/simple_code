package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.authService.requestTemplateAuthConfig;

import ir.smarttrustco.pssnote.core.mapper.BasicMapper;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.requestTemplateAuthConfig.dto.RequestTemplateAuthConfigDto;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.mapperHelper.ExternalOrganizationCommunicationEnumMapHelper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ExternalOrganizationCommunicationEnumMapHelper.class})
public interface RequestTemplateAuthConfigMapper extends BasicMapper<RequestTemplateAuthConfigEntity, RequestTemplateAuthConfigDto> {
}
