package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig;

import com.example.demo.core.service.BasicService;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig.dto.ResponseTokenConfigDto;

public interface ResponseTokenConfigService extends BasicService<ResponseTokenConfigEntity, String> {
    ResponseTokenConfigEntity save(ResponseTokenConfigDto dto);
}
