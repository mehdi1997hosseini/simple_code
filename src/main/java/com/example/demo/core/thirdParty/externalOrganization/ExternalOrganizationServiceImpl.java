package com.example.demo.core.thirdParty.externalOrganization;

import com.example.demo.core.service.BasicServiceImpl;
import com.example.demo.core.thirdParty.externalOrganization.cache.ExternalOrganizationCatchService;
import com.example.demo.core.thirdParty.externalOrganization.dto.ExternalOrganizationDto;
import com.example.demo.core.thirdParty.externalOrganization.mapper.ExternalOrganizationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalOrganizationServiceImpl extends BasicServiceImpl<ExternalOrganizationEntity, String, ExternalOrganizationRepository> implements ExternalOrganizationService, ExternalOrganizationTokenService {
    private final ExternalOrganizationMapper mapper;
    private final ExternalOrganizationCatchService externalOrganizationCatchService;

    public ExternalOrganizationServiceImpl(ExternalOrganizationRepository repository,
                                           ExternalOrganizationMapper mapper, ExternalOrganizationCatchService externalOrganizationCatchService) {
        super(repository);
        this.mapper = mapper;
        this.externalOrganizationCatchService = externalOrganizationCatchService;
    }

    @Override
    public ExternalOrganizationDto saveOrUpdate(ExternalOrganizationDto externalOrganization) {

        ExternalOrganizationEntity entity = mapper.toEntity(externalOrganization);
        ExternalOrganizationEntity externalOrganizationEntity = findExternalOrganizationByOrgName(entity.getOrgName());

        if (externalOrganizationEntity == null || externalOrganizationEntity.getId() == null) {
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
            System.out.println(e.getMessage());
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
