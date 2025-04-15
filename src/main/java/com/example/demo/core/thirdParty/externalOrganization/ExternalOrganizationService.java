package com.example.demo.core.thirdParty.externalOrganization;

import com.example.demo.core.service.BasicService;
import com.example.demo.core.thirdParty.externalOrganization.dto.ExternalOrganizationDto;

public interface ExternalOrganizationService extends BasicService<ExternalOrganizationEntity,String> {
    public ExternalOrganizationDto create(ExternalOrganizationDto externalOrganization);
}
