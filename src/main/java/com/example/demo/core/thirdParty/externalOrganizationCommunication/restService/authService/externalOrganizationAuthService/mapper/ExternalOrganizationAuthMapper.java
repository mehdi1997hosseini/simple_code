package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.mapper;

import com.example.demo.core.mapper.BasicMapper;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.dto.ExternalOrganizationAuthServiceDto;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestAuthConfig.RequestAuthConfigMapper;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestTemplateAuthConfig.RequestTemplateAuthConfigMapper;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig.mapper.ResponseTokenConfigMapper;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.mapperHelper.ExternalOrganizationCommunicationEnumMapHelper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ExternalOrganizationCommunicationEnumMapHelper.class, RequestAuthConfigMapper.class,
        RequestTemplateAuthConfigMapper.class, ResponseTokenConfigMapper.class})
public interface ExternalOrganizationAuthMapper extends BasicMapper<ExternalOrganizationAuthServiceEntity, ExternalOrganizationAuthServiceDto> {
    @Override
    @Mappings({
            @Mapping(source = "orgName", target = "orgName", qualifiedByName = "stringToExternalOrganizationName"),
            @Mapping(source = "tokenType", target = "tokenType", qualifiedByName = "stringToTokenType"),
            @Mapping(source = "httpMethod", target = "httpMethod", qualifiedByName = "stringToHttpMethod"),
            @Mapping(source = "authType", target = "authType", qualifiedByName = "stringToAuthType")
    })
    ExternalOrganizationAuthServiceEntity toEntity(ExternalOrganizationAuthServiceDto externalOrganizationAuthServiceDto);

    @Override
    @Mappings({
            @Mapping(source = "orgName", target = "orgName", qualifiedByName = "externalOrganizationNameToString"),
            @Mapping(source = "tokenType", target = "tokenType", qualifiedByName = "tokenTypeToString"),
            @Mapping(source = "httpMethod", target = "httpMethod", qualifiedByName = "httpMethodToString"),
            @Mapping(source = "authType", target = "authType", qualifiedByName = "authTypeToString")
    })
    ExternalOrganizationAuthServiceDto toDto(ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "httpMethod", target = "httpMethod", qualifiedByName = "stringToHttpMethod")
    void updateFromDto(ExternalOrganizationAuthServiceDto dto, @MappingTarget ExternalOrganizationAuthServiceEntity entity);

}
