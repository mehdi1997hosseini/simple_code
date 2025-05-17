package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.response;

import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.parser.ExternalUnifiedResponseFailed;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseFailedNcrOrganization implements ExternalUnifiedResponseFailed {
    private String errorCode;
    private String errorMessage;

    @Override
    public String getErrorCode() {
        return errorCode;
    }
}
