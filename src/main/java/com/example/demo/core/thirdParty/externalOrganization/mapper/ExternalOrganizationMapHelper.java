package com.example.demo.core.thirdParty.externalOrganization.mapper;


import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;
import com.example.demo.core.thirdParty.externalOrganization.TokenType;
import com.example.demo.core.thirdParty.externalOrganization.token.tokenStrategy.AuthType;
import com.example.demo.core.utility.TimeUnitType;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper
public class ExternalOrganizationMapHelper {

    @Named("stringToExternalOrganizationName")
    public static ExternalOrganizationName stringToExternalOrganizationName(String value) {
        return value != null ? ExternalOrganizationName.fromString(value) : null;
    }

    @Named("externalOrganizationNameToString")
    public static String externalOrganizationNameToString(ExternalOrganizationName value) {
        return value == null ? null : value.name();
    }

    @Named("stringToTokenType")
    public static TokenType stringToTokenType(String value) {
        return value != null ? TokenType.fromString(value) : null;
    }

    @Named("tokenTypeToString")
    public static String tokenTypeToString(TokenType value) {
        return value == null ? null : value.name();
    }

    @Named("stringToTimeUnitType")
    public static TimeUnitType stringToTimeUnitType(String value) {
        return value != null ? TimeUnitType.fromString(value) : null;
    }

    @Named("timeUnitTypeToString")
    public static String timeUnitTypeToString(TimeUnitType value) {
        return value == null ? null : value.name();
    }

    @Named("authTypeToString")
    public static String authTypeToString(AuthType value) {
        return value == null ? null : value.name();
    }

    @Named("stringToAuthType")
    public static AuthType stringToAuthType(String value) {
        return value == null ? null : AuthType.fromString(value);
    }

}
