package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.dao;

import com.example.demo.core.service.BasicInfrastructureServiceImpl;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestHeaderApiConfigInfrastructureServiceImpl extends BasicInfrastructureServiceImpl<RequestHeaderApiConfigEntity, String, RequestHeaderApiConfigRepository> implements RequestHeaderApiConfigInfrastructureService {

    public RequestHeaderApiConfigInfrastructureServiceImpl(Class<RequestHeaderApiConfigEntity> entityClass, RequestHeaderApiConfigRepository repository) {
        super(entityClass, repository);
    }

    @Autowired
    public RequestHeaderApiConfigInfrastructureServiceImpl(RequestHeaderApiConfigRepository repository) {
        super(repository);
    }


}
