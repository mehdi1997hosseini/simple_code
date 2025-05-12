package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.dao;

import com.example.demo.core.service.BasicInfrastructureServiceImpl;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalOrganizationApiServiceInfrastructureServiceImpl extends BasicInfrastructureServiceImpl<ExternalOrganizationApiServiceEntity, String, ExternalOrganizationApiServiceRepository> implements ExternalOrganizationApiServiceInfrastructureService {

    @Autowired
    public ExternalOrganizationApiServiceInfrastructureServiceImpl(ExternalOrganizationApiServiceRepository repository) {
        super(repository);
    }

    public ExternalOrganizationApiServiceInfrastructureServiceImpl(Class<ExternalOrganizationApiServiceEntity> entityClass, ExternalOrganizationApiServiceRepository repository) {
        super(entityClass, repository);
    }


    @Override
    public List<ExternalOrganizationApiServiceEntity> findByExternalOrgName(ExternalOrganizationName extOrgName) {
        try {
            return getEntityManager().createQuery("select e from ExternalOrganizationApiServiceEntity e where e.orgName = :externalOrgName", ExternalOrganizationApiServiceEntity.class)
                    .setParameter("externalOrgName", extOrgName)
                    .getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
