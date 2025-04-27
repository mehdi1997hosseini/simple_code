package com.example.demo.core.thirdParty.externalOrganization.mapper;


import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;
import com.example.demo.core.thirdParty.externalOrganization.TokenType;
import com.example.demo.core.thirdParty.externalOrganization.token.tokenStrategy.AuthType;
import com.example.demo.core.thirdParty.requestTokenConfig.ContentType;
import com.example.demo.core.utility.TimeUnitType;
import org.mapstruct.Named;
import org.springframework.http.HttpMethod;


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

    @Named("httpMethodToString")
    public static String httpMethodToString(HttpMethod value) {
        return value == null ? null : value.name();
    }

    @Named("stringToHttpMethod")
    public static HttpMethod stringToHttpMethod(String value) {
        return value == null ? null : HttpMethod.valueOf(value);
    }

    @Named("contentTypeToString")
    public static String contentTypeToString(ContentType value) {
        return value == null ? null : value.name();
    }

    @Named("stringToContentType")
    public static ContentType stringToContentType(String value) {
        return value == null ? null : ContentType.fromString(value);
    }

//    @Mapping(target = "httpMethod", source = "httpMethod")
//    public HttpMethod map(String value) {
//        if (value != null) {
//            try {
//                return HttpMethod.valueOf(value.toUpperCase());
//            } catch (IllegalArgumentException e) {
//                // در صورتی که رشته ورودی غیرمعتبر باشد، خطا می‌دهیم یا می‌توانیم مقدار پیش‌فرضی انتخاب کنیم
//                return HttpMethod.POST;
//            }
//        }
//        return null;  // اگر null باشد، null برمی‌گردانیم
//    }

}
