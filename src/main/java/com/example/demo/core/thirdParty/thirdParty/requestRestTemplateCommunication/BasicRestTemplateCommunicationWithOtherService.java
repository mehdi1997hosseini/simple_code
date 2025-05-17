package com.example.demo.core.thirdParty.thirdParty.requestRestTemplateCommunication;

import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import org.springframework.http.ResponseEntity;

interface BasicRestTemplateCommunicationWithOtherService {
    <T, R> R sendRequestByBody(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> objectResponseType);

    <T, R> ResponseEntity<R> sendRequest(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> responseBody);

    <T, R> ResponseEntity<R> sendRequestByStrategyRequestSendType(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> objectResponseType);

}
