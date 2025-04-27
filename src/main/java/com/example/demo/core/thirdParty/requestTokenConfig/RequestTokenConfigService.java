package com.example.demo.core.thirdParty.requestTokenConfig;

import com.example.demo.core.service.BasicService;
import com.example.demo.core.thirdParty.requestTokenConfig.dto.RequestTokenConfigDto;

public interface RequestTokenConfigService extends BasicService<RequestTokenConfigEntity, String> {
    void save(RequestTokenConfigDto dto);
}
