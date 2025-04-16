package com.example.demo.core.thirdParty.externalOrganization.cache;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ExternalOrganizationCacheServiceImpl implements ExternalOrganizationCacheService {
    private final Map<ExternalOrganizationName, ExternalOrganizationEntity> extOrgCache = new ConcurrentHashMap<>();

    private final ExternalOrganizationService extOrgService;

    public ExternalOrganizationCacheServiceImpl(ExternalOrganizationService extOrgService) {
        this.extOrgService = extOrgService;
    }

    @Override
    public ExternalOrganizationEntity getExternalOrganizationByOrgName(ExternalOrganizationName organizationName) {
        return extOrgCache.get(organizationName);
    }

    @Override
    public void refreshAllExternalOrganization() {
        List<ExternalOrganizationEntity> findAllExtOrg = extOrgService.findAllInCache();
        if (findAllExtOrg.isEmpty()) return;

        if (extOrgCache.isEmpty()){
            findAllExtOrg.forEach(externalOrganizationEntity -> {
                extOrgCache.put(externalOrganizationEntity.getOrgName(), externalOrganizationEntity);
            });
        }else {
            findAllExtOrg.forEach(externalOrganizationEntity -> {
                if (extOrgCache.containsKey(externalOrganizationEntity.getOrgName())){
                    checkBeforeAdd(externalOrganizationEntity);
                }else
                    extOrgCache.put(externalOrganizationEntity.getOrgName(), externalOrganizationEntity);
            });
        }
    }

    @Override
    public void refreshExternalOrganizationByOrgName(ExternalOrganizationName organizationName) {
        if (extOrgCache.containsKey(organizationName)){
            ExternalOrganizationEntity externalOrganizationByOrgName = extOrgService.findExternalOrganizationByOrgName(organizationName);
            checkBeforeAdd(externalOrganizationByOrgName);
        }
    }

    @Override
    public Map<ExternalOrganizationName, ExternalOrganizationEntity> getAllExternalOrganization() {
        return extOrgCache;
    }

    private void checkBeforeAdd(ExternalOrganizationEntity externalOrganizationEntity){
        if (!extOrgCache.get(externalOrganizationEntity.getOrgName()).equals(externalOrganizationEntity)){
            extOrgCache.replace(externalOrganizationEntity.getOrgName(), externalOrganizationEntity);
        }
    }
}
