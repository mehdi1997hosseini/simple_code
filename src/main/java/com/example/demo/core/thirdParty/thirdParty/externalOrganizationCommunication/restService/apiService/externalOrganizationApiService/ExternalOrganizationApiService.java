package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService;

import ir.smarttrustco.pssnote.core.service.BasicDtoService;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.dto.ExternalOrganizationApiServiceDto;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationName;

import java.util.List;

public interface ExternalOrganizationApiService extends BasicDtoService<ExternalOrganizationApiServiceDto, String> {

    List<ExternalOrganizationApiServiceDto> findByExternalOrgName(ExternalOrganizationName extOrgName);

    void refreshManuallyAllCache();
}
