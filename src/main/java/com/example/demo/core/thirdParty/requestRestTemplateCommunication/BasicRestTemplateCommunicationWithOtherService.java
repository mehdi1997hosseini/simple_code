package com.example.demo.core.thirdParty.requestRestTemplateCommunication;

import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.ExternalTokenDto;

public interface BasicRestTemplateCommunicationWithOtherService {
    <T, R> R sendRequestByBody(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> objectResponseType);

    <T, R> R sendRequestByStrategyRequestSendType(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity,
                                                  Class<R> objectResponseType, ExternalTokenDto dto);
}
