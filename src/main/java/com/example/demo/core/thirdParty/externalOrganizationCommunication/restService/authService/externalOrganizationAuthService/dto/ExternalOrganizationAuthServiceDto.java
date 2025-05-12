package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.dto;

import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestAuthConfig.dto.RequestAuthConfigDto;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestTemplateAuthConfig.dto.RequestTemplateAuthConfigDto;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig.dto.ResponseTokenConfigDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalOrganizationAuthServiceDto implements Serializable {
    @NotNull
    private String orgName;
    @NotNull
    private String authType;
    @NotNull
    private String tokenType;
    @NotNull
    private String authUri;
    private String httpMethod;

    private RequestTemplateAuthConfigDto requestTemplate;
    private RequestAuthConfigDto requestTokenConfig;
    private ResponseTokenConfigDto responseTokenConfig;


}
