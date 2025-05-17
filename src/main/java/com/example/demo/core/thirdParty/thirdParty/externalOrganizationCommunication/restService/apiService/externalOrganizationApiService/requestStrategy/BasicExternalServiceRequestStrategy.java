package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.requestStrategy;

import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.ExternalTokenDto;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.TokenHeaderName;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.TokenType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public interface BasicExternalServiceRequestStrategy {
    <T, R> ResponseEntity<R> sendRequest(T request, ExternalOrganizationApiServiceEntity config, Class<R> responseType
            , RestTemplate restTemplate, ExternalTokenDto token);

    HttpHeaders buildHeaders(ExternalOrganizationApiServiceEntity config, ExternalTokenDto token);

    default HttpHeaders buildHeadersDefault(Map<String, String> headersRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(headersRequest);
        return headers;
    }

    default HttpHeaders getTokenHeader(RequestHeaderApiConfigEntity requestHeaderApiConfig, HttpHeaders headers, String token) {
        String tokenHeaderName = requestHeaderApiConfig.getTokenHeaderName().equals(TokenHeaderName.CUSTOM_NAME_DEFINITION) ?
                requestHeaderApiConfig.getTokenHeaderCustomName() :
                requestHeaderApiConfig.getTokenHeaderName().getValue();

        String tokenType = requestHeaderApiConfig.getTokenType().equals(TokenType.CUSTOM) ?
                (fullTokenRequest(requestHeaderApiConfig.getTokenTypeCustom(), token)) :
                fullTokenRequest(requestHeaderApiConfig.getTokenType().getType(), token);

        headers.set(tokenHeaderName, tokenType);
        return headers;
    }

    private String fullTokenRequest(String tokenType, String token) {
        return (tokenType == null || tokenType.isBlank()) ? token :
                (tokenType.toLowerCase().startsWith("token:") ? tokenType + token : tokenType + " " + token);
    }
}
