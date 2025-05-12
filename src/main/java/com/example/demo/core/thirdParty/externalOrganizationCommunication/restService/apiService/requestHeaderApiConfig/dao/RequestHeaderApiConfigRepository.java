package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.dao;

import com.example.demo.core.repository.BasicRepository;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestHeaderApiConfigRepository extends BasicRepository<RequestHeaderApiConfigEntity, String> {
}
