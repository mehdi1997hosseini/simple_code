package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig.mapper;

import com.example.demo.core.mapper.BasicMapper;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig.ResponseTokenConfigEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig.dto.ResponseTokenConfigDto;
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
