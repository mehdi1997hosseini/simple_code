package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig.mapper;

import ir.smarttrustco.pssnote.core.mapper.BasicMapper;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig.ResponseTokenConfigEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig.dto.ResponseTokenConfigDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ExternalOrganizationAuthMapHelper.class})
public interface ResponseTokenConfigMapper extends BasicMapper<ResponseTokenConfigEntity, ResponseTokenConfigDto> {
    @Override
    @Mapping(source = "timeUnitType", target = "timeUnitType", qualifiedByName = "timeUnitTypeToString")
    ResponseTokenConfigDto toDto(ResponseTokenConfigEntity responseTokenConfigEntity);

    @Override
    @Mapping(source = "timeUnitType", target = "timeUnitType", qualifiedByName = "stringToTimeUnitType")
    ResponseTokenConfigEntity toEntity(ResponseTokenConfigDto responseTokenConfigDto);
}
