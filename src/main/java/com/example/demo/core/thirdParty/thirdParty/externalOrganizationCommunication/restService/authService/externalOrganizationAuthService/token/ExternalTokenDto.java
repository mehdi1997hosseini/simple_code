package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token;

import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ExternalTokenDto implements Serializable {
    private String token;
    private Instant expiresAt;
    private Boolean isValidToken;
    private Integer countTry = 0;

}
