package com.example.demo.core.thirdParty.thirdParty.requestRestTemplateCommunication;

import ir.smarttrustco.pssnote.core.exceptionHandler.exception.AppRunTimeException;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.parser.ExternalOrganizationCommunicationResponseParser;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.parser.ExternalUnifiedResponseFailed;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationName;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.exception.ExternalOrganizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommunicationExternalOrganizationServiceImpl implements CommunicationExternalOrganizationService {

    private final BasicRestTemplateCommunicationWithOtherService basicRestTemplateCommunicationWithOtherService;
    private final Map<ExternalOrganizationName, ExternalOrganizationCommunicationResponseParser> parserMap;

    @Autowired
    public CommunicationExternalOrganizationServiceImpl(BasicRestTemplateCommunicationWithOtherService basicRestTemplateCommunicationWithOtherService,
                                                        List<ExternalOrganizationCommunicationResponseParser> parserList) {
        this.basicRestTemplateCommunicationWithOtherService = basicRestTemplateCommunicationWithOtherService;
        this.parserMap = new HashMap<>();
        parserList.forEach(parser -> parserMap.put(parser.getOrganizationName(), parser));

    }

    @Override
    public <T, R> R sendRequest(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> responseBody) {
        try {
            return basicRestTemplateCommunicationWithOtherService.sendRequest(requestBody, externalOrganizationApiServiceEntity, responseBody).getBody();
        } catch (Exception e) {
            throw new AppRunTimeException(ExternalOrganizationException.EXTERNAL_ORGANIZATION_SERVER_ERROR, responseBody.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public <T, R, F extends ExternalUnifiedResponseFailed> Object sendRequest(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> responseAccept, Class<F> responseFailed) {
        ExternalOrganizationName externalOrganizationName = externalOrganizationApiServiceEntity.getApiEndpointType().getExternalOrganizationName();
        ResponseEntity<String> stringResponseEntity = basicRestTemplateCommunicationWithOtherService.sendRequest(requestBody, externalOrganizationApiServiceEntity, String.class);
        return parserMap.get(externalOrganizationName).parseResponse(stringResponseEntity.getBody(), responseAccept, responseFailed);
    }

}
