package com.example.demo.core.thirdParty.externalOrganization;

import com.example.demo.core.service.BasicServiceImpl;
import com.example.demo.core.thirdParty.externalOrganization.dto.ExternalOrganizationDto;
import com.example.demo.core.thirdParty.externalOrganization.mapper.ExternalOrganizationMapper;
import org.springframework.stereotype.Service;

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


}
