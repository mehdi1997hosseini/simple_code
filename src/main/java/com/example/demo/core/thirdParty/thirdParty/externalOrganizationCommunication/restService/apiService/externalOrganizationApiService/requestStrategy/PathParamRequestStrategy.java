package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.requestStrategy;

import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.ExternalTokenDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
class PathParamRequestStrategy implements BasicExternalServiceRequestStrategy {
    @Override
    public <T, R> ResponseEntity<R> sendRequest(T request, ExternalOrganizationApiServiceEntity config, Class<R> responseType, RestTemplate restTemplate, ExternalTokenDto token) {
        HttpHeaders headers = buildHeaders(config, token);
        String uri = config.getFullApiUri();

        if (request instanceof Map<?, ?> mapRequest) {
            for (Map.Entry<?, ?> entry : mapRequest.entrySet()) {
                uri = uri.replace("{" + entry.getKey().toString() + "}", entry.getValue().toString());
            }
        }

        return restTemplate.exchange(uri, config.getHttpMethod(), new HttpEntity<>(headers), responseType);
    }

    @Override
    public HttpHeaders buildHeaders(ExternalOrganizationApiServiceEntity config, ExternalTokenDto token) {
        HttpHeaders headers = new HttpHeaders();
        RequestHeaderApiConfigEntity headerConfig = config.getRequestHeader();
        headers.set(headerConfig.getContentTypeParamName(), headerConfig.getContentType().getValue());
        headers = getTokenHeader(headerConfig,headers,token.getToken());
        headers.setAll(headerConfig.getDefaultHeaders());
        return headers;
    }


}
