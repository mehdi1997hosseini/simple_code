package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.requestStrategy;

import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.ExternalTokenDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Component
class QueryParamRequestStrategy implements BasicExternalServiceRequestStrategy {

    @Override
    public <T, R> ResponseEntity<R> sendRequest(T request, ExternalOrganizationApiServiceEntity config, Class<R> responseType, RestTemplate restTemplate, ExternalTokenDto token) {
        HttpHeaders headers = buildHeaders(config, token);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(config.getFullApiUri());
        if (request instanceof Map<?, ?> mapRequest) {
            for (Map.Entry<?, ?> entry : mapRequest.entrySet()) {
                builder.queryParam(entry.getKey().toString(), entry.getValue());
            }
        }

        URI uri = builder.build().encode().toUri();
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
