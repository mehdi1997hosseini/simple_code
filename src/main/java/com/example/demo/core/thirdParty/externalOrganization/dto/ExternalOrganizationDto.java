package com.example.demo.core.thirdParty.externalOrganization.dto;

import com.example.demo.core.utility.TimeUnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExternalOrganizationDto implements Serializable {
    private String orgName;
    private String authUrl;
    private String clientId;
    private String clientSecret;
    private String tokenType;
    private String username;
    private String password;
    private String timeUnitType;

}
