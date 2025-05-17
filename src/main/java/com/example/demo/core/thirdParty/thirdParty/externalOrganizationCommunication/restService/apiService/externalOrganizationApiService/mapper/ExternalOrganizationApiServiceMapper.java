package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.mapper;

import ir.smarttrustco.pssnote.core.mapper.BasicMapper;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.dto.ExternalOrganizationApiServiceDto;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.mapper.RequestHeaderApiConfigMapper;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.dto.ExternalOrganizationAuthServiceDto;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.mapperHelper.ExternalOrganizationCommunicationEnumMapHelper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ExternalOrganizationCommunicationEnumMapHelper.class , RequestHeaderApiConfigMapper.class})
public interface ExternalOrganizationApiServiceMapper extends BasicMapper<ExternalOrganizationApiServiceEntity, ExternalOrganizationApiServiceDto> {

    @Override
    @Mappings(value = {@Mapping(source = "httpMethod", target = "httpMethod", qualifiedByName = "httpMethodToString")})
    ExternalOrganizationApiServiceDto toDto(ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity);

    @Override
    @Mappings(value = {@Mapping(source = "httpMethod", target = "httpMethod", qualifiedByName = "stringToHttpMethod")})
    ExternalOrganizationApiServiceEntity toEntity(ExternalOrganizationApiServiceDto externalOrganizationApiServiceDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "httpMethod", target = "httpMethod", qualifiedByName = "stringToHttpMethod")
    void updateFromDto(ExternalOrganizationApiServiceDto dto, @MappingTarget ExternalOrganizationApiServiceEntity entity);


}
