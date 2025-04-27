package com.example.demo.core.thirdParty.requestTemplateJsonConfig;

import com.example.demo.core.entity.BasicEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "TBL_REQUEST_TEMPLATE_JSON_CONFIG")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestTemplateJsonConfigEntity extends BasicEntity<String> {
    private String clientIdParamName;
    private String clientSecretParamName;
    private String usernameParamName;
    private String passwordParamName;
    private String grantTypeParamName;
    private String contentTypeParamName;
    private String scopeParamName;
    private String redirectUriParamName;
    private String samlRequestXmlParamName;
    private String staticTokenParamName;
    private String apiKeyParamName;
    private String apiSecretParamName;
    private String authCodeParamName;

}
