package com.example.demo.core.thirdParty.externalOrganization.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExternalTokenDto implements Serializable {
    private String token;
    private Instant expiresAt;

}
