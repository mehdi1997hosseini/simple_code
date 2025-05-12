package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig;

import com.example.demo.core.service.BasicServiceImpl;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig.dto.ResponseTokenConfigDto;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig.mapper.ResponseTokenConfigMapper;
import org.springframework.stereotype.Service;

@Service
public class ResponseTokenConfigServiceImpl extends BasicServiceImpl<ResponseTokenConfigEntity, String, ResponseTokenConfigRepository> implements ResponseTokenConfigService {
    private final ResponseTokenConfigMapper mapper;

    public ResponseTokenConfigServiceImpl(ResponseTokenConfigRepository repository, ResponseTokenConfigMapper mapper) {
        super(repository);
        this.mapper = mapper;
    }

    @Override
    public ResponseTokenConfigEntity save(ResponseTokenConfigDto dto) {
        return repository.save(mapper.toEntity(dto));
    }

}
