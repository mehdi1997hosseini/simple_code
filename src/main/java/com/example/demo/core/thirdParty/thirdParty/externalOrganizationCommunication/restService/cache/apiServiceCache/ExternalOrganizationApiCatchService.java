package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.cache.apiServiceCache;

import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationApiEndpointType;

import java.util.List;

public interface ExternalOrganizationApiCatchService {

    Boolean saveOrUpdate(ExternalOrganizationApiServiceEntity entity);

    ExternalOrganizationApiServiceEntity findByApiEndPointType(ExternalOrganizationApiEndpointType serviceNameOrEndPoint);

    Boolean isServiceApiEndpointExist(ExternalOrganizationApiEndpointType serviceNameOrEndPoint);

    Boolean refreshExtOrgServiceApiEndPointTypeByEntity(ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity);
    void refreshAllCache(List<ExternalOrganizationApiServiceEntity> listEntity);

}
