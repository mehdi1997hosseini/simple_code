package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig;

import ir.smarttrustco.pssnote.core.service.BasicDtoService;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.dto.RequestHeaderApiConfigDto;

public interface RequestHeaderApiConfigService extends BasicDtoService<RequestHeaderApiConfigDto, String> {
    RequestHeaderApiConfigEntity saveAndFlush(RequestHeaderApiConfigDto requestHeaderApiConfigDto);
}
