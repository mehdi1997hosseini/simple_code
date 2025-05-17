package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.dto;

import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.TokenHeaderName;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Builder
public class RequestHeaderApiConfigDto implements Serializable {

    private String tokenHeaderName;
    private String tokenHeaderCustomName;
    private String contentType;
    private String contentTypeParamName;
    private String tokenType;
    private String tokenTypeCustom;

}
