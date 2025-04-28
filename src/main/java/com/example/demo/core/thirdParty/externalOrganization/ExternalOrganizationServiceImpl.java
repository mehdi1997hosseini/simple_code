package com.example.demo.core.thirdParty.externalOrganization;

import com.example.demo.core.service.BasicServiceImpl;
import com.example.demo.core.thirdParty.externalOrganization.cache.ExternalOrganizationCatchService;
import com.example.demo.core.thirdParty.externalOrganization.dto.ExternalOrganizationDto;
import com.example.demo.core.thirdParty.externalOrganization.mapper.ExternalOrganizationMapper;
import com.example.demo.core.thirdParty.requestTemplateJsonConfig.RequestTemplateJsonConfigService;
import com.example.demo.core.thirdParty.requestTokenConfig.RequestTokenConfigService;
import com.example.demo.core.thirdParty.responseTokenConfig.ResponseTokenConfigService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalOrganizationServiceImpl extends BasicServiceImpl<ExternalOrganizationEntity, String, ExternalOrganizationRepository> implements ExternalOrganizationService, ExternalOrganizationTokenService {
    private final ExternalOrganizationMapper mapper;
    private final ExternalOrganizationCatchService externalOrganizationCatchService;
    private final RequestTokenConfigService requestTokenService;
    private final RequestTemplateJsonConfigService requestTemplateService;
    private final ResponseTokenConfigService responseTokenService;

    public ExternalOrganizationServiceImpl(ExternalOrganizationRepository repository,
                                           ExternalOrganizationMapper mapper, ExternalOrganizationCatchService externalOrganizationCatchService, RequestTokenConfigService requestTokenService, RequestTemplateJsonConfigService requestTemplateService, ResponseTokenConfigService responseTokenService) {
        super(repository);
        this.mapper = mapper;
        this.externalOrganizationCatchService = externalOrganizationCatchService;
        this.requestTokenService = requestTokenService;
        this.requestTemplateService = requestTemplateService;
        this.responseTokenService = responseTokenService;
    }

    @Override
    public ExternalOrganizationDto saveOrUpdate(ExternalOrganizationDto externalOrganization) {

        ExternalOrganizationEntity entity = mapper.toEntity(externalOrganization);
        ExternalOrganizationEntity externalOrganizationEntity = findExternalOrganizationByOrgName(entity.getOrgName());

        if (externalOrganizationEntity == null || externalOrganizationEntity.getId() == null) {
            entity.setRequestTemplate(requestTemplateService.save(externalOrganization.getRequestTemplate()));
            entity.setRequestTokenConfig(requestTokenService.save(externalOrganization.getRequestTokenConfig()));
            entity.setResponseTokenConfig(responseTokenService.save(externalOrganization.getResponseTokenConfig()));

            save(entity);
            externalOrganizationCatchService.saveOrUpdate(entity);
            return mapper.toDto(entity);
        } else {
            mapper.updateFromDto(externalOrganization, externalOrganizationEntity);
            save(externalOrganizationEntity);
            externalOrganizationCatchService.refreshExternalOrganizationByEntity(externalOrganizationEntity);
            return mapper.toDto(externalOrganizationEntity);
        }
    }

    @Override
    public List<ExternalOrganizationDto> findAll() {
        List<ExternalOrganizationEntity> allExtOrg = findAllInCache();
        if (!allExtOrg.isEmpty()) return null;

        return mapper.toDto(allExtOrg);
    }

    @Override
    public List<ExternalOrganizationEntity> findAllInCache() {
        return repository.findAll().stream().filter(entity -> !entity.getIsDeleted()).toList();
    }

    @Override
    public ExternalOrganizationEntity findExternalOrganizationByOrgName(ExternalOrganizationName orgName) {
        try {
            return getEntityManager().createQuery("SELECT e FROM ExternalOrganizationEntity e WHERE e.orgName = :orgName", ExternalOrganizationEntity.class)
                    .setParameter("orgName", orgName)
                    .getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void refreshManuallyExternalOrganization(String organizationName) {
        ExternalOrganizationName orgName = ExternalOrganizationName.fromString(organizationName);
        ExternalOrganizationEntity externalOrganizationByOrgName = findExternalOrganizationByOrgName(orgName);
        externalOrganizationCatchService.refreshExternalOrganizationByEntity(externalOrganizationByOrgName);
    }
}
