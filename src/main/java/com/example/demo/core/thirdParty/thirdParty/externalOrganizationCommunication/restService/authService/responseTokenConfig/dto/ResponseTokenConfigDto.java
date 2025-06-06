package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseTokenConfigDto implements Serializable {
    @NotNull
    private String tokenFieldName;
    private String expireTimeFieldName;
    @NotNull
    private String timeUnitType;
    private Integer expiresIn;

}
