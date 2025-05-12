package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestAuthConfig;

import com.example.demo.core.service.BasicServiceImpl;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestAuthConfig.dto.RequestAuthConfigDto;
import org.springframework.stereotype.Service;

@Service
public class RequestAuthConfigServiceImpl extends BasicServiceImpl<RequestAuthConfigEntity, String, RequestAuthConfigRepository> implements RequestAuthConfigService {

    private final RequestAuthConfigMapper mapper;

    public RequestAuthConfigServiceImpl(RequestAuthConfigRepository repository, RequestAuthConfigMapper mapper) {
        super(repository);
        this.mapper = mapper;
    }


    @Override
    public RequestAuthConfigEntity save(RequestAuthConfigDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

}
