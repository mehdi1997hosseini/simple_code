package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.enums;

import lombok.Getter;

import java.util.List;

@Getter
public enum ExternalOrganizationName {
    BANK("bank"),
    NCR("ncr"),
    CBI("cbi");

    private final String title;

    ExternalOrganizationName(String title) {
        this.title = title;
    }


    public static ExternalOrganizationName fromString(String name) {
        for (ExternalOrganizationName orgName : ExternalOrganizationName.values()) {
            if (orgName.name().equalsIgnoreCase(name) || orgName.title.equalsIgnoreCase(name)) {
                return orgName;
            }
        }
        throw new IllegalArgumentException("Unknown token type: " + name);
    }
}
