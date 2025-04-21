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
            @Mapping(source = "timeUnitType", target = "timeUnitType", qualifiedByName = "stringToTimeUnitType"),
            @Mapping(source = "tokenType", target = "tokenType", qualifiedByName = "stringToTokenType")
    })
    ExternalOrganizationEntity toEntity(ExternalOrganizationDto externalOrganizationDto);

    @Override
    @Mappings({
            @Mapping(source = "orgName", target = "orgName", qualifiedByName = "externalOrganizationNameToString"),
            @Mapping(source = "timeUnitType", target = "timeUnitType", qualifiedByName = "timeUnitTypeToString"),
            @Mapping(source = "tokenType", target = "tokenType", qualifiedByName = "tokenTypeToString")
    })
    ExternalOrganizationDto toDto(ExternalOrganizationEntity externalOrganizationEntity);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(ExternalOrganizationDto dto, @MappingTarget ExternalOrganizationEntity entity);

}
