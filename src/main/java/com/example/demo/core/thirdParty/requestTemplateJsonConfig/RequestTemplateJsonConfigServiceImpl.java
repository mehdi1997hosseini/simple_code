package com.example.demo.core.thirdParty.requestTemplateJsonConfig;

import com.example.demo.core.service.BasicServiceImpl;
import com.example.demo.core.thirdParty.requestTemplateJsonConfig.dto.RequestTemplateJsonConfigDto;
import org.springframework.stereotype.Service;

@Service
public class RequestTemplateJsonConfigServiceImpl extends BasicServiceImpl<RequestTemplateJsonConfigEntity, String, RequestTemplateJsonConfigRepository> implements RequestTemplateJsonConfigService {

    private final RequestTemplateJsonConfigMapper mapper;

    public RequestTemplateJsonConfigServiceImpl(RequestTemplateJsonConfigRepository repository, RequestTemplateJsonConfigMapper mapper) {
        super(repository);
        this.mapper = mapper;
    }

    @Override
    public RequestTemplateJsonConfigEntity save(RequestTemplateJsonConfigDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

}
