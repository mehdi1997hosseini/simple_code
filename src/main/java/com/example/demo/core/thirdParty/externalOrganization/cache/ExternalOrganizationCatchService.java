package com.example.demo.core.thirdParty.externalOrganization.cache;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;

import java.util.List;
import java.util.Map;

public interface ExternalOrganizationCatchService {
    Boolean saveOrUpdate(ExternalOrganizationEntity externalOrganizationEntity);
    Boolean refreshExternalOrganizationByEntity(ExternalOrganizationEntity externalOrganizationEntity);
    Boolean removeExternalOrganizationFromCatch(ExternalOrganizationName orgName);
    void refreshAllCatch(List<ExternalOrganizationEntity> findAllExtOrg);


    public Boolean isExternalOrganizationExist(ExternalOrganizationName externalOrganizationName);
    public Map<ExternalOrganizationName,ExternalOrganizationEntity> findAllExternalOrganization();
    public ExternalOrganizationEntity findExternalOrganizationByOrgName(ExternalOrganizationName organizationName);

}
