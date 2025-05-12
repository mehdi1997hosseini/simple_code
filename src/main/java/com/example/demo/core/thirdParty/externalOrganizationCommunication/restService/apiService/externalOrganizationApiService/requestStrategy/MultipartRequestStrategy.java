package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.requestStrategy;

import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.ExternalOrganizationApiServiceEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.ExternalTokenDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
class MultipartRequestStrategy implements BasicExternalServiceRequestStrategy {
    @Override
    public <T, R> ResponseEntity<R> sendRequest(T request, ExternalOrganizationApiServiceEntity config, Class<R> responseType, RestTemplate restTemplate, ExternalTokenDto token) {
        HttpHeaders headers = buildHeaders(config, token);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        if (!(request instanceof MultiValueMap)) {
            throw new IllegalArgumentException("Multipart requests must be of type MultiValueMap");
        }

        return restTemplate.exchange(
                config.getFullApiUri(),
                config.getHttpMethod(),
                new HttpEntity<>(request, headers),
                responseType
        );
    }

    @Override
    public HttpHeaders buildHeaders(ExternalOrganizationApiServiceEntity config, ExternalTokenDto token) {
        HttpHeaders headers = new HttpHeaders();
        RequestHeaderApiConfigEntity headerConfig = config.getRequestHeader();
        headers.set(headerConfig.getTokenHeaderName().getValue(), headerConfig.getTokenType().getType() + " " + token.getToken());
        headers.setAll(headerConfig.getDefaultHeaders());
        return headers;
    }
}
