package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
public class RequestHeaderApiConfigDto implements Serializable {

    private String tokenHeaderName;
    private String contentType;
    private String contentTypeParamName;
    private String tokenType;

}
