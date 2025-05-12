package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestAuthConfig;

import com.example.demo.core.mapper.BasicMapper;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestAuthConfig.dto.RequestAuthConfigDto;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.mapperHelper.ExternalOrganizationCommunicationEnumMapHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ExternalOrganizationCommunicationEnumMapHelper.class})
public interface RequestAuthConfigMapper extends BasicMapper<RequestAuthConfigEntity, RequestAuthConfigDto> {
    @Override
    @Mapping(source = "contentType", target = "contentType", qualifiedByName = "stringToContentType")
    RequestAuthConfigEntity toEntity(RequestAuthConfigDto dto);

    @Override
    @Mapping(source = "contentType", target = "contentType", qualifiedByName = "contentTypeToString")
    RequestAuthConfigDto toDto(RequestAuthConfigEntity requestAuthConfigEntity);

}
