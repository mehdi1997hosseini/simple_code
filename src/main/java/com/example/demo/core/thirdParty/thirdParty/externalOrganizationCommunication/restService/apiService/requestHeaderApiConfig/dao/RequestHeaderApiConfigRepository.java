package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.dao;

import ir.smarttrustco.pssnote.core.repository.BasicRepository;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestHeaderApiConfigRepository extends BasicRepository<RequestHeaderApiConfigEntity,String> {
}
