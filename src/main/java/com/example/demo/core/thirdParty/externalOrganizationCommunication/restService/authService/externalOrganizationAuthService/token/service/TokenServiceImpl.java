package com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.service;

import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.ExternalOrganizationAuthServiceEntity;
import com.example.demo.core.thirdParty.externalOrganizationCommunication.restService.authService.externalOrganizationAuthService.token.ExternalTokenDto;
import com.example.demo.core.utility.DateTimeZoneUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
    private final RestTemplate restTemplate;

    public TokenServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExternalTokenDto fetchTokenByRest(ExternalOrganizationAuthServiceEntity extOrgEntity) {
        try {
            Map<String, String> body = new HashMap<>();
            body.put("client_id", extOrgEntity.getRequestTokenConfig().getClientId());
            body.put("client_secret", extOrgEntity.getRequestTokenConfig().getClientSecret());
            body.put("username", extOrgEntity.getRequestTokenConfig().getUsername());
            body.put("password", extOrgEntity.getRequestTokenConfig().getPassword());
            body.put("grant_type", "password");

            ResponseEntity<Map> response = restTemplate.postForEntity(
                    extOrgEntity.getAuthUri(), body, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                String token = (String) response.getBody().get("access_token");
                Object expiresInRes = response.getBody().get("expires_in");
                Object expiresAtRes = response.getBody().get("expires_at");

                Instant expiresAt = null;

                if (expiresInRes != null) {
                    Integer expiresIn = (Integer) expiresInRes;
                    expiresAt = DateTimeZoneUtil.DurationAndInstantUtils.calculateExpiry(
                            expiresIn, extOrgEntity.getResponseTokenConfig().getTimeUnitType(), 50);
                } else if (expiresAtRes != null) {
                    String expiresAtStr = (String) expiresAtRes;
                    expiresAt = DateTimeZoneUtil.DurationAndInstantUtils.calculateExpiry(
                            Instant.parse(expiresAtStr), 50);
                }

                // اگر اطلاعات ناقص بود، هیچ توکنی برنگردونه
                if (token == null || expiresAt == null) {
                    return ExternalTokenDto.builder()
                            .token(null)
                            .expiresAt(null)
                            .isValidToken(false).build();
                }

                return new ExternalTokenDto(token, expiresAt, true, 0);
            } else {
                log.error("پاسخ نامعتبر از سرور دریافت شد");
                return ExternalTokenDto.builder()
                        .token(null)
                        .expiresAt(null)
                        .isValidToken(false).build();
            }
        } catch (Exception ex) {
            log.error("خطا در دریافت توکن از سرور مقصد: {}", ex.getMessage());
            return ExternalTokenDto.builder().token(null)
                    .expiresAt(null)
                    .isValidToken(false).build();
        }
    }

    @Override
    public ExternalTokenDto fetchTokenByRest(ExternalOrganizationAuthServiceEntity extOrgEntity, HttpMethod method) {
        HttpEntity<Map<String, String>> httpEntity = extOrgEntity.getAuthType().getHttpEntity(extOrgEntity);
        ResponseEntity<Map> response = restTemplate.exchange(extOrgEntity.getAuthUri(), method, httpEntity, Map.class);
        return parseResponse(response, extOrgEntity);
    }

    public ExternalTokenDto fetchTokenBySoap(ExternalOrganizationAuthServiceEntity extOrgEntity) {
        return new ExternalTokenDto(null, null, true, 0);
    }

    private ExternalTokenDto parseResponse(ResponseEntity<Map> response, ExternalOrganizationAuthServiceEntity externalOrganization) {
        if (!response.getStatusCode().is2xxSuccessful() && response.getBody() == null) {
            return ExternalTokenDto.builder().token(null).expiresAt(null).isValidToken(false).build();
        }

        String token = (String) response.getBody().get(externalOrganization.getResponseTokenConfig().getTokenFieldName());

        Instant expiresAt = null;

        if (externalOrganization.getResponseTokenConfig().getTimeUnitType().name().contains("CUSTOM_")) {
            expiresAt = customTimeExpires(externalOrganization);
        } else {
            expiresAt = getExpiresFromField(response, externalOrganization);
        }


        // اگر اطلاعات ناقص بود، هیچ توکنی برنگردونه
        if (token == null || expiresAt == null) {
            log.error("request id : {} - Token or Expiry Time is not valid ", MDC.get("requestId"));

            return ExternalTokenDto.builder()
                    .token(null)
                    .expiresAt(null)
                    .isValidToken(false).build();
        }

        log.info("GET TOKEN FROM SERVER - EXPIRE IN TIME : {} ", expiresAt);
        return new ExternalTokenDto(token, expiresAt, true, 0);
    }

    private Instant customTimeExpires(ExternalOrganizationAuthServiceEntity externalOrganization) {
        return DateTimeZoneUtil.DurationAndInstantUtils.calculateExpiry(
                externalOrganization.getResponseTokenConfig().getExpiresIn(), externalOrganization.getResponseTokenConfig().getTimeUnitType(), 80);
    }

    private Instant getExpiresFromField(ResponseEntity<Map> response, ExternalOrganizationAuthServiceEntity externalOrganization) {
        Object expiresInRes = response.getBody().get(externalOrganization.getResponseTokenConfig().getExpireTimeFieldName());
        Object expiresAtRes = response.getBody().get(externalOrganization.getResponseTokenConfig().getExpireTimeFieldName());


        if (expiresInRes != null) {
            return DateTimeZoneUtil.DurationAndInstantUtils.calculateExpiry(
                    (Integer) expiresInRes, externalOrganization.getResponseTokenConfig().getTimeUnitType(), 50);
        } else if (expiresAtRes != null) {
            String expiresAtStr = (String) expiresAtRes;
            return DateTimeZoneUtil.DurationAndInstantUtils.calculateExpiry(
                    Instant.parse(expiresAtStr), 50);
        } else
            return null;
    }


}
