package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.cache.authConfigCache;

import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.TokenSchedulerService;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationName;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ExternalOrganizationAuthCatchServiceImpl implements ExternalOrganizationAuthCatchService {
    private final Map<ExternalOrganizationName, ExternalOrganizationAuthServiceEntity> extOrgCatch = new ConcurrentHashMap<>();

    private final TokenSchedulerService tokenSchedulerService;

    public ExternalOrganizationAuthCatchServiceImpl(TokenSchedulerService tokenSchedulerService) {
        this.tokenSchedulerService = tokenSchedulerService;
    }

    @Override
    public Boolean saveOrUpdate(ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity) {
        try {
            if (externalOrganizationAuthServiceEntity == null || externalOrganizationAuthServiceEntity.getId() == null ||
                    externalOrganizationAuthServiceEntity.getOrgName() == null)
                return false;

            if (Boolean.TRUE.equals(isExternalOrganizationExist(externalOrganizationAuthServiceEntity.getOrgName()))) {
                update(externalOrganizationAuthServiceEntity);
            } else {
                extOrgCatch.put(externalOrganizationAuthServiceEntity.getOrgName(), externalOrganizationAuthServiceEntity);
                resetManually(externalOrganizationAuthServiceEntity);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean refreshExternalOrganizationByEntity(ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity) {
        if (Boolean.FALSE.equals(isExternalOrganizationExist(externalOrganizationAuthServiceEntity.getOrgName())))
            return saveOrUpdate(externalOrganizationAuthServiceEntity);
        else
            update(externalOrganizationAuthServiceEntity);

        return resetManually(externalOrganizationAuthServiceEntity);
    }

    private Boolean resetManually(ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity) {
        if (Boolean.TRUE.equals(externalOrganizationAuthServiceEntity.getIsDeleted()))
            return tokenSchedulerService.shotDownManually(externalOrganizationAuthServiceEntity.getOrgName(), externalOrganizationAuthServiceEntity);

        return tokenSchedulerService.resetTokenManually(externalOrganizationAuthServiceEntity.getOrgName(), externalOrganizationAuthServiceEntity);
    }

    @Override
    public Boolean removeExternalOrganizationFromCatch(ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity) {
        extOrgCatch.remove(externalOrganizationAuthServiceEntity.getOrgName());
        if (Boolean.FALSE.equals(isExternalOrganizationExist(externalOrganizationAuthServiceEntity.getOrgName())))
            return tokenSchedulerService.shotDownManually(externalOrganizationAuthServiceEntity.getOrgName(), externalOrganizationAuthServiceEntity);

        return false;
    }

    @Override
    public ExternalOrganizationAuthServiceEntity findExternalOrganizationByOrgName(ExternalOrganizationName organizationName) {
        return extOrgCatch.get(organizationName);
    }

    @Override
    public Boolean isExternalOrganizationExist(ExternalOrganizationName externalOrganizationName) {
        return extOrgCatch
                .containsKey(externalOrganizationName);
    }

    @Override
    public void refreshAllCatch(List<ExternalOrganizationAuthServiceEntity> findAllExtOrg) {
        if (findAllExtOrg == null || findAllExtOrg.isEmpty()) return;

        if (extOrgCatch.isEmpty()) {
            findAllExtOrg.forEach(externalOrganizationEntity -> {
                extOrgCatch.put(externalOrganizationEntity.getOrgName(), externalOrganizationEntity);
            });
        } else {
            findAllExtOrg.forEach(this::saveOrUpdate);
        }
    }

    @Override
    public Map<ExternalOrganizationName, ExternalOrganizationAuthServiceEntity> findAllExternalOrganization() {
        return extOrgCatch;
    }

    private boolean update(ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity) {
        try {
            extOrgCatch.replace(externalOrganizationAuthServiceEntity.getOrgName(), externalOrganizationAuthServiceEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
