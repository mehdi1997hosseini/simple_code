package com.example.demo.core.thirdParty.responseTokenConfig;

import com.example.demo.core.mapper.BasicMapper;
import com.example.demo.core.thirdParty.externalOrganization.mapper.ExternalOrganizationMapHelper;
import com.example.demo.core.thirdParty.responseTokenConfig.dto.ResponseTokenConfigDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ExternalOrganizationMapHelper.class})
public interface ResponseTokenConfigMapper extends BasicMapper<ResponseTokenConfigEntity, ResponseTokenConfigDto> {
    @Override
    @Mapping(source = "timeUnitType", target = "timeUnitType", qualifiedByName = "timeUnitTypeToString")
    ResponseTokenConfigDto toDto(ResponseTokenConfigEntity responseTokenConfigEntity);

    @Override
    @Mapping(source = "timeUnitType", target = "timeUnitType", qualifiedByName = "stringToTimeUnitType")
    ResponseTokenConfigEntity toEntity(ResponseTokenConfigDto responseTokenConfigDto);
}
