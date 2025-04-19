package com.example.demo.core.thirdParty.externalOrganization;

import com.example.demo.core.service.BasicServiceImpl;
import com.example.demo.core.thirdParty.externalOrganization.dto.ExternalOrganizationDto;
import com.example.demo.core.thirdParty.externalOrganization.mapper.ExternalOrganizationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalOrganizationServiceImpl extends BasicServiceImpl<ExternalOrganizationEntity, String, ExternalOrganizationRepository> implements ExternalOrganizationService {
    private final ExternalOrganizationMapper mapper;

    public ExternalOrganizationServiceImpl(ExternalOrganizationRepository repository,
                                           ExternalOrganizationMapper mapper) {
        super(repository);
        this.mapper = mapper;
    }

    @Override
    public ExternalOrganizationDto create(ExternalOrganizationDto externalOrganization) {
        ExternalOrganizationEntity entity = mapper.toEntity(externalOrganization);
        save(entity);
        return externalOrganization;
    }

    @Override
    public List<ExternalOrganizationDto> findAll() {
        List<ExternalOrganizationEntity> allExtOrg = repository.findAll();
        if (!allExtOrg.isEmpty()) return null;

        return mapper.toDto(allExtOrg);
    }

    @Override
    public List<ExternalOrganizationEntity> findAllInCache() {
        return repository.findAll().stream().filter(entity -> !entity.getIsDeleted()).toList();
    }

    @Override
    public ExternalOrganizationEntity findExternalOrganizationByOrgName(ExternalOrganizationName orgName) {
        return getEntityManager().createQuery("SELECT e FROM ExternalOrganizationEntity e WHERE e.orgName = :orgName", ExternalOrganizationEntity.class)
                .setParameter("orgName", orgName)
                .getSingleResult();
    }

}
