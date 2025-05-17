package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.parser;

import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.ExternalOrganizationName;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.ResponseType;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ExternalUnifiedResponse implements Serializable {

    private boolean isSuccess;
    private String organizationName;
    private Object responseBody;
    private ResponseType responseType;

}
