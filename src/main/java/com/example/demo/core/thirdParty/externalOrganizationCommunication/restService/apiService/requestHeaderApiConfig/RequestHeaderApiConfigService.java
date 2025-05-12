package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig;

import com.example.demo.core.service.BasicDtoService;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.dto.RequestHeaderApiConfigDto;

public interface RequestHeaderApiConfigService extends BasicDtoService<RequestHeaderApiConfigEntity, String, RequestHeaderApiConfigDto> {
    RequestHeaderApiConfigEntity saveAndFlush(RequestHeaderApiConfigDto requestHeaderApiConfigDto);
}
