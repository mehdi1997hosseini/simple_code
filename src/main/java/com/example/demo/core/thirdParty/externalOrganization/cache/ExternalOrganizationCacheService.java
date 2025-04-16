package com.example.demo.core.thirdParty.externalOrganization.cache;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;

import java.util.Map;

public interface ExternalOrganizationCacheService {
    public void refreshAllExternalOrganization();
    void refreshExternalOrganizationByOrgName(ExternalOrganizationName organizationName);

    public Map<ExternalOrganizationName,ExternalOrganizationEntity> getAllExternalOrganization();
    public ExternalOrganizationEntity getExternalOrganizationByOrgName(ExternalOrganizationName organizationName);

}
