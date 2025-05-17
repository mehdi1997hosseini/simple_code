package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig;

import ir.smarttrustco.pssnote.core.service.BasicService;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig.dto.ResponseTokenConfigDto;

public interface ResponseTokenConfigService extends BasicService<ResponseTokenConfigEntity,String> {
    ResponseTokenConfigEntity save(ResponseTokenConfigDto dto);
}
