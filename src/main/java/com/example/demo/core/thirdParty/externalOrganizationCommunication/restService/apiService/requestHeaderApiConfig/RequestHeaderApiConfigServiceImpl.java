package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig;

import com.example.demo.core.service.BasicDtoServiceImpl;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.dao.RequestHeaderApiConfigInfrastructureService;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.dto.RequestHeaderApiConfigDto;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.mapper.RequestHeaderApiConfigMapper;
import org.springframework.stereotype.Service;

@Service
public class RequestHeaderApiConfigServiceImpl extends BasicDtoServiceImpl<RequestHeaderApiConfigEntity, String, RequestHeaderApiConfigDto, RequestHeaderApiConfigMapper, RequestHeaderApiConfigInfrastructureService> implements RequestHeaderApiConfigService {

    public RequestHeaderApiConfigServiceImpl(RequestHeaderApiConfigInfrastructureService currentInfrastructureService, RequestHeaderApiConfigMapper mapper) {
        super(currentInfrastructureService, mapper);
    }

    @Override
    public RequestHeaderApiConfigEntity saveAndFlush(RequestHeaderApiConfigDto requestHeaderApiConfigDto) {
        return currentInfrastructureService.save(mapper.toEntity(requestHeaderApiConfigDto));
    }

}
