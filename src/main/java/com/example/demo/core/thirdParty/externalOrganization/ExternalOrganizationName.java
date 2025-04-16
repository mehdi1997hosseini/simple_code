package com.example.demo.core.thirdParty.externalOrganization;

public enum ExternalOrganizationName {
    BANK("bank"), NCR("ncr"), CBI("cbi");
    private String name;

    ExternalOrganizationName(String name) {
        this.name = name;
    }

    public static ExternalOrganizationName fromString(String name) {
        for (ExternalOrganizationName orgName : ExternalOrganizationName.values()) {
            if (orgName.name.equalsIgnoreCase(name)) {
                return orgName;
            }
        }
        throw new IllegalArgumentException("Unknown token type: " + name);
    }
}
