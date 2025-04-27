package com.example.demo.core.thirdParty.requestTemplateJsonConfig;

import com.example.demo.core.mapper.BasicMapper;
import com.example.demo.core.thirdParty.externalOrganization.mapper.ExternalOrganizationMapHelper;
import com.example.demo.core.thirdParty.requestTemplateJsonConfig.dto.RequestTemplateJsonConfigDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ExternalOrganizationMapHelper.class})
public interface RequestTemplateJsonConfigMapper extends BasicMapper<RequestTemplateJsonConfigEntity, RequestTemplateJsonConfigDto> {
}
