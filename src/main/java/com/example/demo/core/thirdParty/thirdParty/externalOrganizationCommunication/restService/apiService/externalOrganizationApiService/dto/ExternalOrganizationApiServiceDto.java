package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.dto;

import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.requestStrategy.RequestSendType;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.dto.RequestHeaderApiConfigDto;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationApiEndpointType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExternalOrganizationApiServiceDto implements Serializable {
    @NotBlank(message = "host-address is null")
    private String hostAddress;
    @NotBlank
    private String contextPath;
    @NotNull
    private ExternalOrganizationApiEndpointType apiEndpointType;
    @NotBlank
    private String httpMethod;
    private Boolean isActive;
    private RequestSendType requestSendType;

    @NotNull
    private RequestHeaderApiConfigDto requestHeader;
}
