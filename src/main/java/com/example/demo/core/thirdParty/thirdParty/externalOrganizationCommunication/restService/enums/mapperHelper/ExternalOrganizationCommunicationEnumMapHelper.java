package com.example.demo.core.thirdParty.thirdParty.externalOrganizationCommunication.restService.enums.mapperHelper;

import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.tokenStrategy.AuthType;
import ir.smarttrustco.pssnote.core.thirdParty.externalOrganizationCommunication.restService.enums.*;
import org.mapstruct.Named;
import org.springframework.http.HttpMethod;

public class ExternalOrganizationCommunicationEnumMapHelper {

    /**
     * Enum class to String
     **/
    @Named("externalOrganizationNameToString")
    public static String externalOrganizationNameToString(ExternalOrganizationName value) {
        return value == null ? null : value.name();
    }

    @Named("tokenTypeToString")
    public static String tokenTypeToString(TokenType value) {
        return value == null ? null : value.name();
    }

    @Named("authTypeToString")
    public static String authTypeToString(AuthType value) {
        return value == null ? null : value.name();
    }

    @Named("httpMethodToString")
    public static String httpMethodToString(HttpMethod value) {
        return value == null ? null : value.name();
    }

    @Named("contentTypeToString")
    public static String contentTypeToString(ContentType value) {
        return value == null ? null : value.name();
    }

    @Named("externalOrganizationEndpointTypeToString")
    public static String externalOrganizationEndpointTypeToString(ExternalOrganizationApiEndpointType value) {
        return value == null ? null : value.name();
    }

    @Named("tokenHeaderNameToString")
    public static String tokenHeaderNameToString(TokenHeaderName value) {
        return value == null ? null : value.name();
    }




    /**
     * String to Enum class
     **/
    @Named("stringToExternalOrganizationName")
    public static ExternalOrganizationName stringToExternalOrganizationName(String value) {
        return value != null ? ExternalOrganizationName.fromString(value) : null;
    }

    @Named("stringToTokenType")
    public static TokenType stringToTokenType(String value) {
        return value != null ? TokenType.fromString(value) : null;
    }

    @Named("stringToAuthType")
    public static AuthType stringToAuthType(String value) {
        return value == null ? null : AuthType.fromString(value);
    }

    @Named("stringToHttpMethod")
    public static HttpMethod stringToHttpMethod(String value) {
        return value == null ? null : HttpMethod.valueOf(value);
    }

    @Named("stringToContentType")
    public static ContentType stringToContentType(String value) {
        return value == null ? null : ContentType.fromString(value);
    }

    @Named("stringToExternalOrganizationEndpointType")
    public static ExternalOrganizationApiEndpointType stringToExternalOrganizationEndpointType(String value) {
        return value == null ? null : ExternalOrganizationApiEndpointType.fromString(value);
    }

    @Named("stringToTokenHeaderName")
    public static TokenHeaderName stringToTokenHeaderName(String value) {
        return value == null ? null : TokenHeaderName.fromString(value);
    }

}
