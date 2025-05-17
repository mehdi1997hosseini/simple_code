package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig;

import ir.smarttrustco.pssnote.core.entity.BasicEntity;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.ContentType;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.TokenHeaderName;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.TokenType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Entity
@Table(name = "TBL_REQUEST_HEADER_API_CONFIG")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RequestHeaderApiConfigEntity extends BasicEntity<String> {

    @Enumerated(EnumType.STRING)
    @Column(name = "TOKEN_HEADER_NAME", nullable = false)
    private TokenHeaderName tokenHeaderName;
    @Column(name = "TOKEN_HEADER_CUSTOM_NAME")
    private String tokenHeaderCustomName;
    @Enumerated(EnumType.STRING)
    @Column(name = "CONTENT_TYPE", nullable = false)
    private ContentType contentType;
    @Column(name = "CONTENT_TYPE_PARAM_NAME")
    private String contentTypeParamName;
    @Enumerated(EnumType.STRING)
    @Column(name = "TOKEN_TYPE", nullable = false)
    private TokenType tokenType;
    private String tokenTypeCustom;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "DEFAULT_OTHER_HEADERS")
    private Map<String, String> defaultHeaders;


}
