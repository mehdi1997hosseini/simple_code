package com.example.demo.core.thirdParty.requestRestTemplateCommunication;

import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.requestStrategy.BasicExternalServiceRequestStrategy;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.ExternalTokenDto;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.cache.tokenCache.TokenCacheService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Component
@AllArgsConstructor
public class BasicRestTemplateCommunicationWithOtherServiceImpl implements BasicRestTemplateCommunicationWithOtherService {

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
    public <T, R> R sendRequestByStrategyRequestSendType(T requestBody, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> objectResponseType, ExternalTokenDto dto) {
        BasicExternalServiceRequestStrategy instanceStrategyRequest = externalOrganizationApiServiceEntity.getRequestSendType().getInstanceStrategyRequest();
        ResponseEntity<R> response = instanceStrategyRequest.sendRequest(requestBody, externalOrganizationApiServiceEntity, objectResponseType, restTemplate, dto);

        return response.getBody();
    }

    private <E, R> HttpEntity<Object> requestExternalOrganizationService(E objectRequest, ExternalOrganizationApiServiceEntity externalOrganizationApiServiceEntity, Class<R> responseTypeClass) {
        HttpHeaders httpHeader = new HttpHeaders();
        RequestHeaderApiConfigEntity requestHeader = externalOrganizationApiServiceEntity.getRequestHeader();

        httpHeader.set(requestHeader.getContentTypeParamName(), requestHeader.getContentType().getValue());
        ExternalTokenDto token = tokenCache.getToken(externalOrganizationApiServiceEntity.getApiEndpointType().getExternalOrganizationName());
        httpHeader.set(requestHeader.getTokenHeaderName().getValue(), requestHeader.getTokenType().getType() + " " + token.getToken());

        Map<String, String> defaultHeaders = requestHeader.getDefaultHeaders();
        if (!defaultHeaders.isEmpty()) {
            for (Map.Entry<String, String> entry : defaultHeaders.entrySet()) {
                httpHeader.set(entry.getKey(), entry.getValue());
            }
        }

        return new HttpEntity<>(objectRequest, httpHeader);
    }

}
