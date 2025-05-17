package com.example.demo.core.thirdParty.thirdParty.requestRestTemplateCommunication;

import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.requestStrategy.BasicExternalServiceRequestStrategy;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.ExternalTokenDto;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.cache.tokenCache.TokenCacheService;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.TokenHeaderName;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.TokenType;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Component
@AllArgsConstructor
class BasicRestTemplateCommunicationWithOtherServiceImpl implements BasicRestTemplateCommunicationWithOtherService {

    private final RestTemplate restTemplate;
    private final TokenCacheService tokenCache;

    @Override
    public <T, R> R sendRequestByBody(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> responseType) {
        HttpEntity<Object> objectHttpEntity = requestExternalOrganizationService(requestBody, externalOrganizationApiServiceEntity, responseType);
        ResponseEntity<R> response = restTemplate.exchange(
                externalOrganizationApiServiceEntity.getFullApiUri(),
                externalOrganizationApiServiceEntity.getHttpMethod(), // یا GET یا هر نوع دیگر بر اساس دیتابیس
                objectHttpEntity,
                responseType
        );

        return response.getBody();
    }

    @Override
    public <T, R> ResponseEntity<R> sendRequest(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity,
                                                Class<R> responseBody) {

        HttpEntity<Object> objectHttpEntity = requestExternalOrganizationService(requestBody,
                externalOrganizationApiServiceEntity, responseBody);
        return restTemplate.exchange(
                externalOrganizationApiServiceEntity.getFullApiUri(),
                externalOrganizationApiServiceEntity.getHttpMethod(),
                objectHttpEntity,
                responseBody
        );
    }

    @Override
    public <T, R> ResponseEntity<R> sendRequestByStrategyRequestSendType(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> objectResponseType) {
        ExternalTokenDto token = tokenCache.getToken(externalOrganizationApiServiceEntity.getApiEndpointType().getExternalOrganizationName());
        BasicExternalServiceRequestStrategy instanceStrategyRequest = externalOrganizationApiServiceEntity.getRequestSendType().getInstanceStrategyRequest();
        assert instanceStrategyRequest != null;

        return instanceStrategyRequest.sendRequest(requestBody, externalOrganizationApiServiceEntity, objectResponseType, restTemplate, token);
    }

    private <E, R> HttpEntity<Object> requestExternalOrganizationService(E objectRequest, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> responseTypeClass) {
        HttpHeaders httpHeader = new HttpHeaders();
        RequestHeaderApiConfigEntity requestHeader = externalOrganizationApiServiceEntity.getRequestHeader();

        httpHeader.set(requestHeader.getContentTypeParamName(), requestHeader.getContentType().getValue());
        ExternalTokenDto token = tokenCache.getToken(externalOrganizationApiServiceEntity.getApiEndpointType().getExternalOrganizationName());
        setTokenToHeader(requestHeader, token.getToken(), httpHeader);

        Map<String, String> defaultHeaders = requestHeader.getDefaultHeaders();
        if (!defaultHeaders.isEmpty()) {
            for (Map.Entry<String, String> entry : defaultHeaders.entrySet()) {
                httpHeader.set(entry.getKey(), entry.getValue());
            }
        }

        return new HttpEntity<>(objectRequest, httpHeader);
    }

    private void setTokenToHeader(RequestHeaderApiConfigEntity requestHeader , String token , HttpHeaders httpHeader) {
        String tokenHeaderName = requestHeader.getTokenHeaderName().equals(TokenHeaderName.CUSTOM_NAME_DEFINITION) ?
                requestHeader.getTokenHeaderCustomName() :
                requestHeader.getTokenHeaderName().getValue();

        String tokenType = requestHeader.getTokenType().equals(TokenType.CUSTOM) ?
                (fullTokenRequest(requestHeader.getTokenTypeCustom(), token)) :
                fullTokenRequest(requestHeader.getTokenType().getType(), token);

        httpHeader.set(tokenHeaderName, tokenType);

    }

    private String fullTokenRequest(String tokenType, String token) {
        return (tokenType == null || tokenType.isBlank()) ? token :
                (tokenType.toLowerCase().startsWith("token:") ? tokenType + token : tokenType + " " + token);
    }

}
