package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.mapper;

import ir.smarttrustco.pssnote.core.mapper.BasicMapper;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.dto.RequestHeaderApiConfigDto;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.mapperHelper.ExternalOrganizationCommunicationEnumMapHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ExternalOrganizationCommunicationEnumMapHelper.class})
public interface RequestHeaderApiConfigMapper extends BasicMapper<RequestHeaderApiConfigEntity, RequestHeaderApiConfigDto> {

    @Override
    @Mappings({@Mapping(source = "tokenHeaderName", target = "tokenHeaderName", qualifiedByName = "stringToTokenHeaderName"),
            @Mapping(source = "contentType", target = "contentType", qualifiedByName = "stringToContentType"),
            @Mapping(source = "tokenType", target = "tokenType", qualifiedByName = "stringToTokenType")})
    RequestHeaderApiConfigEntity toEntity(RequestHeaderApiConfigDto requestHeaderApiConfigDto);

    @Override
    @Mappings({@Mapping(source = "tokenHeaderName", target = "tokenHeaderName", qualifiedByName = "tokenHeaderNameToString"),
            @Mapping(source = "contentType", target = "contentType", qualifiedByName = "contentTypeToString"),
            @Mapping(source = "tokenType", target = "tokenType", qualifiedByName = "tokenTypeToString")})
    RequestHeaderApiConfigDto toDto(RequestHeaderApiConfigEntity requestHeaderApiConfigEntity);

}
