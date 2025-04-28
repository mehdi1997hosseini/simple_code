package com.example.demo.core.thirdParty.responseTokenConfig;

import com.example.demo.core.service.BasicService;
import com.example.demo.core.thirdParty.responseTokenConfig.dto.ResponseTokenConfigDto;

public interface ResponseTokenConfigService extends BasicService<ResponseTokenConfigEntity,String> {
    ResponseTokenConfigEntity save(ResponseTokenConfigDto dto);
}
