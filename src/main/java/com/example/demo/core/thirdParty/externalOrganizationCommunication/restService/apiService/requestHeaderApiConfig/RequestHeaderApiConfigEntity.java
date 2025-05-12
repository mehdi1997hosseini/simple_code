package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig;

import com.example.demo.core.entity.BasicEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.ContentType;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.TokenHeaderName;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.TokenType;
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
    private TokenHeaderName tokenHeaderName;
    @Enumerated(EnumType.STRING)
    private ContentType contentType;
    private String contentTypeParamName;
    private TokenType tokenType;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String, String> defaultHeaders;


}
