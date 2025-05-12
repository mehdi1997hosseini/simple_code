package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestTemplateAuthConfig;

import com.example.demo.core.service.BasicServiceImpl;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestTemplateAuthConfig.dto.RequestTemplateAuthConfigDto;
import org.springframework.stereotype.Service;

@Service
public class RequestTemplateAuthConfigServiceImpl extends BasicServiceImpl<RequestTemplateAuthConfigEntity, String, RequestTemplateAuthConfigRepository> implements RequestTemplateAuthConfigService {

    private final RequestTemplateAuthConfigMapper mapper;

    public RequestTemplateAuthConfigServiceImpl(RequestTemplateAuthConfigRepository repository, RequestTemplateAuthConfigMapper mapper) {
        super(repository);
        this.mapper = mapper;
    }

    @Override
    public RequestTemplateAuthConfigEntity save(RequestTemplateAuthConfigDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

}
