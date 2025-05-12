package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.mapper;

import com.example.demo.core.mapper.BasicMapper;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.dto.ExternalOrganizationApiServiceDto;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.mapper.RequestHeaderApiConfigMapper;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.mapperHelper.ExternalOrganizationCommunicationEnumMapHelper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ExternalOrganizationCommunicationEnumMapHelper.class, RequestHeaderApiConfigMapper.class})
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
