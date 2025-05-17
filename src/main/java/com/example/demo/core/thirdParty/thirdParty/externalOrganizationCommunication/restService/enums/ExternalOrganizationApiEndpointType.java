package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum ExternalOrganizationApiEndpointType {
    GET_PROMISSORY("getPromissory", "get-promissory", ExternalOrganizationName.BANK),
    CREATE_CERTIFICATE("createCertificate", "certificate/request/independent-step-one", ExternalOrganizationName.BANK),
    ;

    private final String serviceName;
    private final String apiEndpoint;
    private final ExternalOrganizationName externalOrganizationName;


    ExternalOrganizationApiEndpointType(String serviceName, String apiEndpoint, ExternalOrganizationName externalOrganizationName) {
        this.serviceName = serviceName;
        this.apiEndpoint = apiEndpoint;
        this.externalOrganizationName = externalOrganizationName;
    }

    public static ExternalOrganizationName getOrganizationByEndpointPath(String value) {
        for (ExternalOrganizationApiEndpointType endpoint : values()) {
            if (endpoint.serviceName.equalsIgnoreCase(value) || endpoint.apiEndpoint.equals(value)) {
                return endpoint.getExternalOrganizationName();
            }
        }
        throw new IllegalArgumentException("Unknown ExternalOrganizationApiEndpointType: " + value);
    }

    public static List<ExternalOrganizationApiEndpointType> getEndpointsByOrganization(ExternalOrganizationName org) {
        return Arrays.stream(values())
                .filter(endpoint -> endpoint.externalOrganizationName == org)
                .toList();
    }

    public static ExternalOrganizationApiEndpointType fromString(String value) {
        for (ExternalOrganizationApiEndpointType extOrgApiEndPointType : values()) {
            if (extOrgApiEndPointType.name().equalsIgnoreCase(value) ||
                    extOrgApiEndPointType.serviceName.equalsIgnoreCase(value) ||
                    extOrgApiEndPointType.apiEndpoint.equalsIgnoreCase(value)) {
                return extOrgApiEndPointType;
            }
        }
        throw new IllegalArgumentException("Unknown ExternalOrganizationApiEndpointType : " + value);
    }

}
