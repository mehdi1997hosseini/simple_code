package com.example.demo.core.thirdParty.externalOrganization;

import com.example.demo.core.entity.BasicEntity;
import com.example.demo.core.thirdParty.externalOrganization.token.tokenStrategy.AuthType;
import com.example.demo.core.thirdParty.requestTemplateJsonConfig.RequestTemplateJsonConfigEntity;
import com.example.demo.core.thirdParty.requestTokenConfig.RequestTokenConfigEntity;
import com.example.demo.core.thirdParty.responseTokenConfig.ResponseTokenConfigEntity;
import com.example.demo.core.utility.TimeUnitType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.HttpMethod;

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
    @Column(name = "HTTP_METHOD")
    private HttpMethod httpMethod;

    @OneToOne(fetch = FetchType.EAGER)
    private RequestTemplateJsonConfigEntity requestTemplate;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private RequestTokenConfigEntity requestTokenConfig;
    @OneToOne(fetch = FetchType.EAGER)
    private ResponseTokenConfigEntity responseTokenConfig;


}
