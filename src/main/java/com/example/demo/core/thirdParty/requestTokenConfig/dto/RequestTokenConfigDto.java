package com.example.demo.core.thirdParty.requestTokenConfig.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestTokenConfigDto implements Serializable {
    private String authCode;
    private String clientId;
    private String clientSecret;
    private String username;
    private String password;
    private String apiKey;
    private String staticToken;
    private String redirectUri;
    private String samlRequestXml;
    private String grantType;
    private String contentType;

}
