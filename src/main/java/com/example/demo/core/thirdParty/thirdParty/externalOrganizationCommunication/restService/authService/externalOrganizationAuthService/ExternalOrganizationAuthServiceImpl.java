package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService;

import ir.smarttrustco.pssnote.core.exceptionHandler.exception.AppRunTimeException;
import ir.smarttrustco.pssnote.core.service.BasicServiceImpl;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.dto.ExternalOrganizationAuthServiceDto;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.cache.authConfigCache.ExternalOrganizationAuthCatchService;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationName;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.exception.ExternalOrganizationException;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.mapper.ExternalOrganizationAuthMapper;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.requestTemplateAuthConfig.RequestTemplateAuthConfigService;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.requestAuthConfig.RequestAuthConfigService;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig.ResponseTokenConfigService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalOrganizationAuthServiceImpl extends BasicServiceImpl<ExternalOrganizationAuthServiceEntity, String, ExternalOrganizationAuthServiceRepository> implements ExternalOrganizationAuthService {
    private final ExternalOrganizationAuthMapper mapper;
    private final ExternalOrganizationAuthCatchService externalOrganizationAuthCatchService;
    private final RequestAuthConfigService requestTokenService;
    private final RequestTemplateAuthConfigService requestTemplateService;
    private final ResponseTokenConfigService responseTokenService;

    public ExternalOrganizationAuthServiceImpl(ExternalOrganizationAuthServiceRepository repository,
                                               ExternalOrganizationAuthMapper mapper, ExternalOrganizationAuthCatchService externalOrganizationAuthCatchService, RequestAuthConfigService requestTokenService, RequestTemplateAuthConfigService requestTemplateService, ResponseTokenConfigService responseTokenService) {
        super(repository);
        this.mapper = mapper;
        this.externalOrganizationAuthCatchService = externalOrganizationAuthCatchService;
        this.requestTokenService = requestTokenService;
        this.requestTemplateService = requestTemplateService;
        this.responseTokenService = responseTokenService;
    }

    @Override
    public ExternalOrganizationAuthServiceDto saveOrUpdate(ExternalOrganizationAuthServiceDto externalOrganization) {

        ExternalOrganizationAuthServiceEntity entity = mapper.toEntity(externalOrganization);
        ExternalOrganizationAuthServiceEntity externalOrganizationAuthServiceEntity = findExternalOrganizationByOrgName(entity.getOrgName()).stream().filter(entity1 -> entity1.getIsDeleted().equals(Boolean.FALSE)).findFirst().orElse(null);

        if (externalOrganizationAuthServiceEntity == null || externalOrganizationAuthServiceEntity.getId() == null) {
            return save(externalOrganization, entity);
        } else if (externalOrganizationAuthServiceEntity.getIsDeleted().equals(Boolean.TRUE)) {
            return save(externalOrganization, entity);
        } else {
            mapper.updateFromDto(externalOrganization, externalOrganizationAuthServiceEntity);
            save(externalOrganizationAuthServiceEntity);
            externalOrganizationAuthCatchService.refreshExternalOrganizationByEntity(externalOrganizationAuthServiceEntity);
            return mapper.toDto(externalOrganizationAuthServiceEntity);
        }
    }

    private ExternalOrganizationAuthServiceDto save(ExternalOrganizationAuthServiceDto externalOrganization, ExternalOrganizationAuthServiceEntity entity) {
        entity.setRequestTemplate(requestTemplateService.save(externalOrganization.getRequestTemplate()));
        entity.setRequestTokenConfig(requestTokenService.save(externalOrganization.getRequestTokenConfig()));
        entity.setResponseTokenConfig(responseTokenService.save(externalOrganization.getResponseTokenConfig()));

        save(entity);
        externalOrganizationAuthCatchService.saveOrUpdate(entity);

        return mapper.toDto((ExternalOrganizationAuthServiceEntity) null);
    }

    @Override
    public List<ExternalOrganizationAuthServiceDto> findAll() {
        List<ExternalOrganizationAuthServiceEntity> allExtOrg = findAllInCacheWhenStartApp();
        if (!allExtOrg.isEmpty()) return null;

        return mapper.toDto(allExtOrg);
    }

    @Override
    public List<ExternalOrganizationAuthServiceEntity> findAllInCacheWhenStartApp() {
        List<ExternalOrganizationAuthServiceEntity> all = repository.findAll();
        return !all.isEmpty() ? all.stream().filter(entity -> !entity.getIsDeleted()).toList() : null;
    }

    @Override
    public List<ExternalOrganizationAuthServiceEntity> findExternalOrganizationByOrgName(ExternalOrganizationName orgName) {
        try {
            return getEntityManager().createQuery("SELECT e FROM ExternalOrganizationAuthServiceEntity e WHERE e.orgName = :orgName", ExternalOrganizationAuthServiceEntity.class)
                    .setParameter("orgName", orgName)
                    .getResultList();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void refreshManuallyExternalOrganization(String organizationName) {
        ExternalOrganizationName orgName = ExternalOrganizationName.fromString(organizationName);
        ExternalOrganizationAuthServiceEntity externalOrganizationByOrgName = findExternalOrganizationByOrgName(orgName).stream().filter(entity -> entity.getIsDeleted().equals(Boolean.FALSE)).findFirst().orElse(null);
        if (externalOrganizationByOrgName == null || externalOrganizationByOrgName.getIsDeleted())
            throw new AppRunTimeException(ExternalOrganizationException.EXTERNAL_ORGANIZATION_NAME_IS_NOT_EXIST, (Object) organizationName);

        Boolean isFinishedOperation = externalOrganizationAuthCatchService.refreshExternalOrganizationByEntity(externalOrganizationByOrgName);
        if (isFinishedOperation.equals(Boolean.FALSE))
            throw new AppRunTimeException(ExternalOrganizationException.FAILED_PROCESS_RESTART_MANUALLY_EXTERNAL_ORGANIZATION, organizationName);

    }

    @Override
    public void shotDownManuallyExternalOrganizationForGetToken(String organizationName) {
        ExternalOrganizationName orgName = ExternalOrganizationName.fromString(organizationName);
        ExternalOrganizationAuthServiceEntity externalOrganizationByOrgName = findExternalOrganizationByOrgName(orgName).stream().filter(entity -> entity.getIsDeleted().equals(Boolean.FALSE)).findFirst().orElse(null);;
        if (externalOrganizationByOrgName == null)
            throw new AppRunTimeException(ExternalOrganizationException.EXTERNAL_ORGANIZATION_NAME_IS_NOT_EXIST, (Object) organizationName);

        Boolean isFinishedOperation = externalOrganizationAuthCatchService.removeExternalOrganizationFromCatch(externalOrganizationByOrgName);
        if (Boolean.FALSE.equals(isFinishedOperation))
            throw new AppRunTimeException(ExternalOrganizationException.FAILED_PROCESS_SHOT_DOWN_MANUALLY_EXTERNAL_ORGANIZATION);
    }
}
