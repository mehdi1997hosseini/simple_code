package com.example.demo.core.thirdParty.requestTemplateJsonConfig;

import com.example.demo.core.service.BasicService;
import com.example.demo.core.thirdParty.requestTemplateJsonConfig.dto.RequestTemplateJsonConfigDto;

public interface RequestTemplateJsonConfigService extends BasicService<RequestTemplateJsonConfigEntity,String> {

    RequestTemplateJsonConfigEntity save(RequestTemplateJsonConfigDto dto);

}
