package com.example.demo.core.thirdParty.externalOrganization;

import com.example.demo.core.service.BasicService;
import com.example.demo.core.thirdParty.externalOrganization.dto.ExternalOrganizationDto;

import java.util.List;

public interface ExternalOrganizationService extends BasicService<ExternalOrganizationEntity,String> {
    ExternalOrganizationDto create(ExternalOrganizationDto externalOrganization);
    List<ExternalOrganizationDto> findAll();
    List<ExternalOrganizationEntity> findAllInCache();
    ExternalOrganizationEntity findExternalOrganizationByOrgName(ExternalOrganizationName orgName);
}
