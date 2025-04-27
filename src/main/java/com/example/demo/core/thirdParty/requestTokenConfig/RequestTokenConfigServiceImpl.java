package com.example.demo.core.thirdParty.requestTokenConfig;

import com.example.demo.core.service.BasicServiceImpl;
import com.example.demo.core.thirdParty.requestTokenConfig.dto.RequestTokenConfigDto;
import org.springframework.stereotype.Service;

@Service
public class RequestTokenConfigServiceImpl extends BasicServiceImpl<RequestTokenConfigEntity,String,RequestTokenConfigRepository> implements  RequestTokenConfigService {

    private final RequestTokenConfigMapper mapper;
    public RequestTokenConfigServiceImpl(RequestTokenConfigRepository repository, RequestTokenConfigMapper mapper) {
        super(repository);
        this.mapper = mapper;
    }


    @Override
    public void save(RequestTokenConfigDto dto) {
        save(mapper.toEntity(dto));
    }

}
