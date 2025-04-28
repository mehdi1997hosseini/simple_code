package com.example.demo.core.thirdParty.requestTokenConfig;

import com.example.demo.core.service.BasicServiceImpl;
import com.example.demo.core.thirdParty.requestTokenConfig.dto.RequestTokenConfigDto;
import org.springframework.stereotype.Service;

@Service
public class RequestTokenConfigServiceImpl extends BasicServiceImpl<RequestTokenConfigEntity, String, RequestTokenConfigRepository> implements RequestTokenConfigService {

    private final RequestTokenConfigMapper mapper;

    public RequestTokenConfigServiceImpl(RequestTokenConfigRepository repository, RequestTokenConfigMapper mapper) {
        super(repository);
        this.mapper = mapper;
    }


    @Override
    public RequestTokenConfigEntity save(RequestTokenConfigDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

}
