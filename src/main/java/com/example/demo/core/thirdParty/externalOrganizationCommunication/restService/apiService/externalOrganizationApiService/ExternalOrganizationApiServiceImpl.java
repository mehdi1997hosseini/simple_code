package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService;

import com.example.demo.core.service.BasicDtoServiceImpl;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.dao.ExternalOrganizationApiServiceInfrastructureService;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.dto.ExternalOrganizationApiServiceDto;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.mapper.ExternalOrganizationApiServiceMapper;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigService;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.cache.apiServiceCache.ExternalOrganizationApiCatchService;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalOrganizationApiServiceImpl extends BasicDtoServiceImpl<ExternalOrganizationApiServiceEntity, String, ExternalOrganizationApiServiceDto, ExternalOrganizationApiServiceMapper, ExternalOrganizationApiServiceInfrastructureService> implements ExternalOrganizationApiService {

    private final RequestHeaderApiConfigService requestHeaderApiConfigService;
    private final ExternalOrganizationApiCatchService currentEntityCache;

    @Autowired
    public ExternalOrganizationApiServiceImpl(ExternalOrganizationApiServiceInfrastructureService currentInfrastructureService, ExternalOrganizationApiServiceMapper mapper, RequestHeaderApiConfigService requestHeaderApiConfigService, ExternalOrganizationApiCatchService currentEntityCache) {
        super(currentInfrastructureService, mapper);
        this.requestHeaderApiConfigService = requestHeaderApiConfigService;
        this.currentEntityCache = currentEntityCache;
    }

    @Override
    public ExternalOrganizationApiServiceDto save(ExternalOrganizationApiServiceDto dto) {
        ExternalOrganizationApiServiceEntity entity = mapper.toEntity(dto);
        entity.setRequestHeader(requestHeaderApiConfigService.saveAndFlush(dto.getRequestHeader()));

        return mapper.toDto(currentInfrastructureService.save(entity));
    }

    @Override
    public List<ExternalOrganizationApiServiceDto> findByExternalOrgName(ExternalOrganizationName extOrgName) {
        List<ExternalOrganizationApiServiceEntity> externalOrganizationApiServiceEntity = currentInfrastructureService.findByExternalOrgName(extOrgName);
        if (externalOrganizationApiServiceEntity == null || externalOrganizationApiServiceEntity.isEmpty())
            return null;
        return mapper.toDto(externalOrganizationApiServiceEntity);
    }

    @Override
    public void refreshManuallyAllCache() {
        currentEntityCache.refreshAllCache(currentInfrastructureService.findAll());
    }
}
