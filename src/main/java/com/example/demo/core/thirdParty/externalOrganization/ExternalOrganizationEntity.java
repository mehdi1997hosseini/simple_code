package com.example.demo.core.thirdParty.externalOrganization;

import com.example.demo.core.entity.BasicEntity;
import com.example.demo.core.thirdParty.externalOrganization.token.tokenStrategy.AuthType;
import com.example.demo.core.utility.TimeUnitType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_EXTERNAL_ORGANIZATION")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExternalOrganizationEntity extends BasicEntity<String> {

    @Enumerated(EnumType.STRING)
    @Column(name = "EXTERNAL_ORGANIZATION_NAME", updatable = false, nullable = false)
    private ExternalOrganizationName orgName;           // نام سازمان خارجی
    @Enumerated(EnumType.STRING)
    @Column(name = "TOKEN_TYPE", nullable = false)
    private TokenType tokenType;      // نوع توکن (مثل Bearer یا JWT)
    @Enumerated(EnumType.STRING)
    @Column(name = "AUTH_TYPE", nullable = false)
    private AuthType authType;      // نوع احراز هویت این سازمان

    @Column(name = "AUTH_URI")
    private String authUri;        // URI برای دریافت توکن
    @Column(name = "AUTH_CODE")
    private String authCode;
    @Column(name = "CLIENT_ID")
    private String clientId;       // شناسه کلاینت (Client ID)
    @Column(name = "CLIENT_SECRET")
    private String clientSecret;   // رمز کلاینت (Client Secret)
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "API_KEY")
    private String apiKey;
    @Column(name = "STATIC_TOKEN")
    private String staticToken;
    @Column(name = "REDIRECT_URI")
    private String redirectUri;
    @Column(name = "SAML_REQUEST_XML")
    private String SamlRequestXml;

    @Enumerated(EnumType.STRING)
    @Column(name = "TIME_UNIT_TYPE", nullable = false)
    private TimeUnitType timeUnitType;

//    @Enumerated(EnumType.STRING)
//    private GrantType grantType;

}
