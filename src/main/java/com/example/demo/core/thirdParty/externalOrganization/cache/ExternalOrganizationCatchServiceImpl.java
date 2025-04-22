package com.example.demo.core.thirdParty.externalOrganization.cache;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;
import com.example.demo.core.thirdParty.externalOrganization.token.TokenSchedulerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ExternalOrganizationCatchServiceImpl implements ExternalOrganizationCatchService {
    private final Map<ExternalOrganizationName, ExternalOrganizationEntity> extOrgCatch = new ConcurrentHashMap<>();

    private final TokenSchedulerService tokenSchedulerService;

    public ExternalOrganizationCatchServiceImpl(TokenSchedulerService tokenSchedulerService) {
        this.tokenSchedulerService = tokenSchedulerService;
    }

    @Override
    public Boolean saveOrUpdate(ExternalOrganizationEntity externalOrganizationEntity) {
        try {
            if (externalOrganizationEntity == null || externalOrganizationEntity.getId() == null ||
                    externalOrganizationEntity.getOrgName() == null)
                return false;

            if (isExternalOrganizationExist(externalOrganizationEntity.getOrgName())) {
                update(externalOrganizationEntity);
            } else {
                extOrgCatch.put(externalOrganizationEntity.getOrgName(), externalOrganizationEntity);
                tokenSchedulerService.resetTokenManually(externalOrganizationEntity.getOrgName(), externalOrganizationEntity);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean refreshExternalOrganizationByEntity(ExternalOrganizationEntity externalOrganizationEntity) {
        if (isExternalOrganizationExist(externalOrganizationEntity.getOrgName())) {
            update(externalOrganizationEntity);
            tokenSchedulerService.resetTokenManually(externalOrganizationEntity.getOrgName(), externalOrganizationEntity);
            return true;
        }
        return false;
    }

    @Override
    public Boolean removeExternalOrganizationFromCatch(ExternalOrganizationName orgName) {
        return extOrgCatch.remove(orgName, findExternalOrganizationByOrgName(orgName));
    }

    @Override
    public ExternalOrganizationEntity findExternalOrganizationByOrgName(ExternalOrganizationName organizationName) {
        return extOrgCatch.get(organizationName);
    }

    @Override
    public Boolean isExternalOrganizationExist(ExternalOrganizationName externalOrganizationName) {
        return extOrgCatch
                .containsKey(externalOrganizationName);
    }

    @Override
    public void refreshAllCatch(List<ExternalOrganizationEntity> findAllExtOrg) {
        if (findAllExtOrg.isEmpty()) return;

        if (extOrgCatch.isEmpty()) {
            findAllExtOrg.forEach(externalOrganizationEntity -> {
                extOrgCatch.put(externalOrganizationEntity.getOrgName(), externalOrganizationEntity);
            });
        } else {
            findAllExtOrg.forEach(this::saveOrUpdate);
        }
    }

    @Override
    public Map<ExternalOrganizationName, ExternalOrganizationEntity> findAllExternalOrganization() {
        return extOrgCatch;
    }

    private boolean update(ExternalOrganizationEntity externalOrganizationEntity) {
        try {
            extOrgCatch.replace(externalOrganizationEntity.getOrgName(), externalOrganizationEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
