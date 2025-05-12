package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.dao;


import com.example.demo.core.service.BasicInfrastructureService;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationName;

import java.util.List;

public interface ExternalOrganizationApiServiceInfrastructureService extends BasicInfrastructureService<ExternalOrganizationApiServiceEntity, String> {

    List<ExternalOrganizationApiServiceEntity> findByExternalOrgName(ExternalOrganizationName extOrgName);
}
