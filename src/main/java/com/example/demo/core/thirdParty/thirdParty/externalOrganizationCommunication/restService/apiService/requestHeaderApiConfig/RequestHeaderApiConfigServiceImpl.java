package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig;

import ir.smarttrustco.pssnote.core.service.BasicDtoServiceImpl;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.dao.RequestHeaderApiConfigInfrastructureService;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.dto.RequestHeaderApiConfigDto;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.mapper.RequestHeaderApiConfigMapper;
import org.springframework.stereotype.Service;

@Service
public class RequestHeaderApiConfigServiceImpl extends BasicDtoServiceImpl<RequestHeaderApiConfigEntity,String, RequestHeaderApiConfigDto, RequestHeaderApiConfigMapper, RequestHeaderApiConfigInfrastructureService> implements RequestHeaderApiConfigService {

    public RequestHeaderApiConfigServiceImpl(RequestHeaderApiConfigInfrastructureService currentInfrastructureService, RequestHeaderApiConfigMapper mapper) {
        super(currentInfrastructureService, mapper);
    }

    @Override
    public RequestHeaderApiConfigEntity saveAndFlush(RequestHeaderApiConfigDto requestHeaderApiConfigDto) {
        return currentInfrastructureService.save(mapper.toEntity(requestHeaderApiConfigDto));
    }

}
