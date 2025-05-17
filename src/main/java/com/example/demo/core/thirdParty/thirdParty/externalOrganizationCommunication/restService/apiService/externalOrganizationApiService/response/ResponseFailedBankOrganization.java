package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.response;

import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.parser.ExternalUnifiedResponseFailed;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseFailedBankOrganization implements ExternalUnifiedResponseFailed {

    private String errorCode;
    private String errorMessageEn;
    private String errorMessageFa;
    private String reason;

    @Override
    public String getErrorCode() {
        return errorCode;
    }
}

