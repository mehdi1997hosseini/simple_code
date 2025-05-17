package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService;

import ir.smarttrustco.pssnote.core.entity.BasicEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.tokenStrategy.AuthType;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.requestAuthConfig.RequestAuthConfigEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.requestTemplateAuthConfig.RequestTemplateAuthConfigEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.responseTokenConfig.ResponseTokenConfigEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationName;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.HttpMethod;

@Entity
@Table(name = "TBL_EXTERNAL_ORGANIZATION_AUTH_SERVICE")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExternalOrganizationAuthServiceEntity extends BasicEntity<String> {

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
    @Column(name = "HTTP_METHOD")
    private HttpMethod httpMethod;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REQUEST_TEMPLATE_ID")
    private RequestTemplateAuthConfigEntity requestTemplate;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "REQUEST_TOKEN_CONFIG_ID")
    private RequestAuthConfigEntity requestTokenConfig;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "RESPONSE_TOKEN_CONFIG_ID")
    private ResponseTokenConfigEntity responseTokenConfig;


}
