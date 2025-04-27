package com.example.demo.core.thirdParty.externalOrganization.dto;

import com.example.demo.core.thirdParty.requestTemplateJsonConfig.dto.RequestTemplateJsonConfigDto;
import com.example.demo.core.thirdParty.requestTokenConfig.dto.RequestTokenConfigDto;
import com.example.demo.core.thirdParty.responseTokenConfig.dto.ResponseTokenConfigDto;
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
    private String httpMethod;

    private RequestTemplateJsonConfigDto requestTemplate;
    private RequestTokenConfigDto requestTokenConfig;
    private ResponseTokenConfigDto responseTokenConfig;



}
