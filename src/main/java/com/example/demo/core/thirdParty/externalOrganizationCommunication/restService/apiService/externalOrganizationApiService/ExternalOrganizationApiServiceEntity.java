package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService;

import com.example.demo.core.entity.BasicEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.requestStrategy.RequestSendType;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.apiService.requestHeaderApiConfig.RequestHeaderApiConfigEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationApiEndpointType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.HttpMethod;

@Entity
@Table(name = "TBL_EXTERNAL_ORGANIZATION_API_SERVICE")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExternalOrganizationApiServiceEntity extends BasicEntity<String> {

    @Column(name = "HOST_ADDRESS")
    private String hostAddress;
    @Column(name = "CONTEXT_PATH")
    private String contextPath;
    @Enumerated(EnumType.STRING)
    @Column(name = "API_ENDPOINT_TYPE")
    private ExternalOrganizationApiEndpointType apiEndpointType;
    @Column(name = "IS_ACTIVE")
    private Boolean isActive = true;
    @Column(name = "HTTP_METHOD")
    private HttpMethod httpMethod;
    @Enumerated(EnumType.STRING)
    @Column(name = "REQUEST_SEND_TYPE")
    private RequestSendType requestSendType;

    @OneToOne
    @JoinColumn(name = "REQUEST_HEADER_API_CONFIG_ID")
    private RequestHeaderApiConfigEntity requestHeader;

    @Getter
    @Transient
    private transient String fullApiUri;

    public String getFullApiUri() {
        StringBuilder uri = new StringBuilder();

        if (!hostAddress.endsWith("/"))
            hostAddress += "/";

        uri.append(hostAddress);

        if (contextPath.endsWith("/")) contextPath = contextPath.substring(0, contextPath.length() - 1);

        uri.append(contextPath.startsWith("/")? contextPath.substring(1) : contextPath );
        String apiEndpoint = apiEndpointType.getApiEndpoint();

        if (!apiEndpoint.startsWith("/")) uri.append("/");
        uri.append(apiEndpoint);

        return uri.toString();
    }
}


