package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.cache.apiServiceCache;

import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationApiEndpointType;

import java.util.List;

public interface ExternalOrganizationApiCatchService {

    Boolean saveOrUpdate(ExternalOrganizationApiServiceEntity entity);

    ExternalOrganizationApiServiceEntity findByApiEndPointType(ExternalOrganizationApiEndpointType serviceNameOrEndPoint);

    Boolean isServiceApiEndpointExist(ExternalOrganizationApiEndpointType serviceNameOrEndPoint);

    Boolean refreshExtOrgServiceApiEndPointTypeByEntity(ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity);

    void refreshAllCache(List<ExternalOrganizationApiServiceEntity> listEntity);

}
