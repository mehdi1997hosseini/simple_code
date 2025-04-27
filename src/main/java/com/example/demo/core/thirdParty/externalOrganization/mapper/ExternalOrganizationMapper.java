package com.example.demo.core.thirdParty.externalOrganization.mapper;

import com.example.demo.core.mapper.BasicMapper;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import com.example.demo.core.thirdParty.externalOrganization.dto.ExternalOrganizationDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ExternalOrganizationMapHelper.class})
public interface ExternalOrganizationMapper extends BasicMapper<ExternalOrganizationEntity, ExternalOrganizationDto> {
    @Override
    @Mappings({
            @Mapping(source = "orgName", target = "orgName", qualifiedByName = "stringToExternalOrganizationName"),
            @Mapping(source = "tokenType", target = "tokenType", qualifiedByName = "stringToTokenType"),
            @Mapping(source = "httpMethod", target = "httpMethod", qualifiedByName = "stringToHttpMethod"),
            @Mapping(source = "authType", target = "authType", qualifiedByName = "stringToAuthType")
    })
    ExternalOrganizationEntity toEntity(ExternalOrganizationDto externalOrganizationDto);

    @Override
    @Mappings({
            @Mapping(source = "orgName", target = "orgName", qualifiedByName = "externalOrganizationNameToString"),
            @Mapping(source = "tokenType", target = "tokenType", qualifiedByName = "tokenTypeToString"),
            @Mapping(source = "httpMethod", target = "httpMethod", qualifiedByName = "httpMethodToString"),
            @Mapping(source = "authType", target = "authType", qualifiedByName = "authTypeToString")
    })
    ExternalOrganizationDto toDto(ExternalOrganizationEntity externalOrganizationEntity);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    @Mapping(source = "httpMethod", target = "httpMethod", qualifiedByName = "stringToHttpMethod")
    void updateFromDto(ExternalOrganizationDto dto, @MappingTarget ExternalOrganizationEntity entity);

}
