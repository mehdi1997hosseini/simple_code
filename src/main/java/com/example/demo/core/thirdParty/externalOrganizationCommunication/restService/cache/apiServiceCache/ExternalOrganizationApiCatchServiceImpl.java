package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.cache.apiServiceCache;

import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationApiEndpointType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ExternalOrganizationApiCatchServiceImpl implements ExternalOrganizationApiCatchService {

    private final Map<ExternalOrganizationApiEndpointType, ExternalOrganizationApiServiceEntity> extOrgServiceMap = new ConcurrentHashMap<>();

    @Override
    public Boolean saveOrUpdate(ExternalOrganizationApiServiceEntity entity) {
        if (Boolean.TRUE.equals(isServiceApiEndpointExist(entity.getApiEndpointType()))) {
            return update(entity);
        } else {
            extOrgServiceMap.put(entity.getApiEndpointType(), entity);
            return true;
        }
    }

    @Override
    public ExternalOrganizationApiServiceEntity findByApiEndPointType(ExternalOrganizationApiEndpointType serviceNameOrEndPoint) {
        return extOrgServiceMap.get(serviceNameOrEndPoint);
    }

    @Override
    public Boolean isServiceApiEndpointExist(ExternalOrganizationApiEndpointType serviceNameOrEndPoint) {
        return extOrgServiceMap.containsKey(serviceNameOrEndPoint);
    }

    @Override
    public Boolean refreshExtOrgServiceApiEndPointTypeByEntity(ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity) {
        return saveOrUpdate(externalOrganizationApiServiceEntity);
    }

    @Override
    public void refreshAllCache(List<ExternalOrganizationApiServiceEntity> listEntity) {
        if (listEntity == null || listEntity.isEmpty()) return;

        listEntity.forEach(this::saveOrUpdate);
    }

    private Boolean update(ExternalOrganizationApiServiceEntity entity) {
        ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity = extOrgServiceMap.get(entity.getApiEndpointType());
        // replace by <key , oldValue , newValue>
        return extOrgServiceMap.replace(entity.getApiEndpointType(), externalOrganizationApiServiceEntity, entity);
    }

}
