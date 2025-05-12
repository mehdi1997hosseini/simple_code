package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService;

import com.example.demo.core.service.BasicDtoService;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.dto.ExternalOrganizationApiServiceDto;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationName;

import java.util.List;

public interface ExternalOrganizationApiService extends BasicDtoService<ExternalOrganizationApiServiceEntity, String, ExternalOrganizationApiServiceDto> {

    List<ExternalOrganizationApiServiceDto> findByExternalOrgName(ExternalOrganizationName extOrgName);

    void refreshManuallyAllCache();
}
