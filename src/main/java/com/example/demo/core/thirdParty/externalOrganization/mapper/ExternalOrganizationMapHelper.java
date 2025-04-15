package com.example.demo.core.thirdParty.externalOrganization.mapper;


import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;
import com.example.demo.core.thirdParty.externalOrganization.TokenType;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public class ExternalOrganizationMapHelper {

    @Named("stringToExternalOrganizationName")
    public static ExternalOrganizationName stringToExternalOrganizationName(String value) {

        return ExternalOrganizationName.fromString(value);
    }

    @Named("externalOrganizationNameToString")
    public static String externalOrganizationNameToString(ExternalOrganizationName value) {
        return value == null ? null : value.name();
    }

    @Named("stringToTokenType")
    public static TokenType stringToTokenType(String value) {
        return TokenType.fromString(value);
    }

    @Named("tokenTypeToString")
    public static String tokenTypeToString(TokenType value) {
        return value == null ? null : value.name();
    }

}
