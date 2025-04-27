package com.example.demo.core.thirdParty.externalOrganization.token.tokenStrategy;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Map;

public enum AuthType {
    BASIC_USERNAME_PASSWORD, CLIENT_CREDENTIALS, OAUTH_PASSWORD, API_KEY, STATIC_TOKEN, CUSTOM,
    SAML, JWT, OAUTH_AUTH_CODE, KERBEROS;

    public static AuthType fromString(String type) {
        if (type == null)
            throw new IllegalArgumentException("Auth type is Null : ");

        for (AuthType authType : AuthType.values()) {
            if (authType.name().equalsIgnoreCase(type)) {
                return authType;
            }
        }
        throw new IllegalArgumentException("Unknown Auth type: " + type);
    }

    private HttpHeaders getHeader(Map<String, String> authParams) {
        HttpHeaders headers = new HttpHeaders();
        switch (this) {
            case BASIC_USERNAME_PASSWORD:
                headers.setContentType(MediaType.APPLICATION_JSON);
                break;
            case OAUTH_AUTH_CODE:
                headers.set("Authorization", "Bearer " + authParams.get("auth_code"));
                break;
            case OAUTH_PASSWORD:
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                break;
            case CLIENT_CREDENTIALS:
                headers.setContentType(MediaType.APPLICATION_JSON);
                break;
            case API_KEY:
                headers.set("x-api-key", authParams.get("api_key"));
                break;
//            case STATIC_TOKEN:
//                headers.set("Authorization", "Bearer " + authParams.get("token"));
//                break;
//            case SAML:
//                headers.set("SAML-Token", authParams.get("saml_token"));
//                break;
//            case JWT:
//                headers.set("Authorization", "Bearer " + authParams.get("jwt_token"));
//                break;
//
//            case KERBEROS:
//                headers.set("Authorization", "Kerberos " + authParams.get("kerberos_token"));
//                break;
//            case CUSTOM:
//                headers.set("Custom-Auth", authParams.get("custom_token"));
//                break;
            default:
                throw new IllegalArgumentException("Unsupported AuthType");
        }
        return headers;
    }

    public HttpEntity<Map<String, String>> getHttpEntity(ExternalOrganizationEntity org) {
        TokenStrategy tokenStrategy = switch (this) {
            case BASIC_USERNAME_PASSWORD -> new BasicUsernamePasswordStrategy();
            case OAUTH_AUTH_CODE -> new OAuthAuthCodeStrategy();
            case OAUTH_PASSWORD -> new OAuthPasswordStrategy();
            case CLIENT_CREDENTIALS -> new ClientCredentialsStrategy();
            case API_KEY -> new APIKeyStrategy();
//            case STATIC_TOKEN:
//                headers.set("Authorization", "Bearer " + authParams.get("token"));
//                break;
//            case SAML:
//                headers.set("SAML-Token", authParams.get("saml_token"));
//                break;
//            case JWT:
//                headers.set("Authorization", "Bearer " + authParams.get("jwt_token"));
//                break;
//
//            case KERBEROS:
//                headers.set("Authorization", "Kerberos " + authParams.get("kerberos_token"));
//                break;
//            case CUSTOM:
//                headers.set("Custom-Auth", authParams.get("custom_token"));
//                break;
            default -> throw new IllegalArgumentException("Unsupported AuthType");
        };
        Map<String, String> body = tokenStrategy.prepareAuthParams(org);
        HttpHeaders headers = new HttpHeaders();
        headers.set(org.getRequestTemplate().getContentTypeParamName(),org.getRequestTokenConfig().getContentType().name());
        return new HttpEntity<>(body, headers);
    }

}
