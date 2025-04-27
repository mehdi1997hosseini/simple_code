package com.example.demo.core.thirdParty.requestTokenConfig;

import com.example.demo.core.mapper.BasicMapper;
import com.example.demo.core.thirdParty.externalOrganization.mapper.ExternalOrganizationMapHelper;
import com.example.demo.core.thirdParty.requestTokenConfig.dto.RequestTokenConfigDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ExternalOrganizationMapHelper.class})
public interface RequestTokenConfigMapper extends BasicMapper<RequestTokenConfigEntity, RequestTokenConfigDto> {
    @Override
    @Mapping(source = "contentType",target = "contentType",qualifiedByName = "stringToContentType")
    RequestTokenConfigEntity toEntity(RequestTokenConfigDto dto);

    @Override
    @Mapping(source = "contentType",target = "contentType",qualifiedByName = "contentTypeToString")
    RequestTokenConfigDto toDto(RequestTokenConfigEntity requestTokenConfigEntity);

}
