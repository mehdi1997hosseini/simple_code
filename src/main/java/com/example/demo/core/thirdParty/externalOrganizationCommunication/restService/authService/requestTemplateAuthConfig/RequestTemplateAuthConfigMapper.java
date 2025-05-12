package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestTemplateAuthConfig;

import com.example.demo.core.mapper.BasicMapper;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestTemplateAuthConfig.dto.RequestTemplateAuthConfigDto;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.mapperHelper.ExternalOrganizationCommunicationEnumMapHelper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ExternalOrganizationCommunicationEnumMapHelper.class})
public interface RequestTemplateAuthConfigMapper extends BasicMapper<RequestTemplateAuthConfigEntity, RequestTemplateAuthConfigDto> {
}
