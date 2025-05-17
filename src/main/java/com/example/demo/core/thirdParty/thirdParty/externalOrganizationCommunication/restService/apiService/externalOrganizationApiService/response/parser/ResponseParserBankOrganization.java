package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.apiService.externalOrganizationApiService.response.parser;

import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.parser.ExternalOrganizationCommunicationResponseParser;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationName;
import org.springframework.stereotype.Component;

@Component
public class ResponseParserBankOrganization implements ExternalOrganizationCommunicationResponseParser {
    @Override
    public ExternalOrganizationName getOrganizationName() {
        return ExternalOrganizationName.BANK;
    }
}
