package com.example.demo.core.thirdParty.externalOrganization.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalOrganizationDto implements Serializable {
    @NotNull
    private String orgName;
    @NotNull
    private String authType;
    @NotNull
    private String tokenType;
    @NotNull
    private String authUri;
    private String authCode;
    private String clientId;
    private String clientSecret;
    private String username;
    private String password;
    private String apiKey;
    private String staticToken;
    private String redirectUri;
    private String SamlRequestXml;

    @NotNull
    private String timeUnitType;

}
