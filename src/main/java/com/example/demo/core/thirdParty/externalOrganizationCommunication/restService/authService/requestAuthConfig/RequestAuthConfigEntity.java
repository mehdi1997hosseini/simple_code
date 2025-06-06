package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestAuthConfig;

import com.example.demo.core.entity.BasicEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.ContentType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TBL_REQUEST_AUTH_CONFIG")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestAuthConfigEntity extends BasicEntity<String> {
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
    private String samlRequestXml;
    @Column(name = "GRANT_TYPE")
    private String grantType;
    @Column(name = "CONTENT_TYPE")
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

//    @Column(name = "TOKEN_REQUEST_CONFIG_JSON")
//    private String tokenRequestConfigJson; // ذخیره تنظیمات مربوط به درخواست به صورت JSON

}
