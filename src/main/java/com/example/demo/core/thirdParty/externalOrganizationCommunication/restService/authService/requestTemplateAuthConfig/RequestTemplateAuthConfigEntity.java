package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.requestTemplateAuthConfig;

import com.example.demo.core.entity.BasicEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "TBL_REQUEST_TEMPLATE_AUTH_CONFIG")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestTemplateAuthConfigEntity extends BasicEntity<String> {
    @Column(name = "CLIENT_ID_PARAM_NAME")
    private String clientIdParamName;
    @Column(name = "CLIENT_SECRET_PARAM_NAME")
    private String clientSecretParamName;
    @Column(name = "USERNAME_PARAM_NAME")
    private String usernameParamName;
    @Column(name = "PASSWORD_PARAM_NAME")
    private String passwordParamName;
    @Column(name = "GRANT_TYPE_PARAM_NAME")
    private String grantTypeParamName;
    @Column(name = "CONTENT_TYPE_PARAM_NAME")
    private String contentTypeParamName;
    @Column(name = "SCOPE_PARAM_NAME")
    private String scopeParamName;
    @Column(name = "REDIRECT_URI_PARAM_NAME")
    private String redirectUriParamName;
    @Column(name = "SAML_REQUEST_XML_PARAM_NAME")
    private String samlRequestXmlParamName;
    @Column(name = "STATIC_TOKEN_PARAM_NAME")
    private String staticTokenParamName;
    @Column(name = "API_KEY_PARAM_NAME")
    private String apiKeyParamName;
    @Column(name = "API_SECRETE_PARAM_NAME")
    private String apiSecretParamName;
    @Column(name = "AUTH_CODE_PARAM_NAME")
    private String authCodeParamName;

}
