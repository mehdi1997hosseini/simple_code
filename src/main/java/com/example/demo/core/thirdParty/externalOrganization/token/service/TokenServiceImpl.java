package com.example.demo.core.thirdParty.externalOrganization.token.service;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;
import com.example.demo.core.thirdParty.externalOrganization.token.ExternalTokenDto;
import com.example.demo.core.utility.DateTimeZoneUtil;
import com.example.demo.core.utility.TimeUnitType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class TokenServiceImpl implements TokenService {
    private final RestTemplate restTemplate;
    private final String accessToken = "access_token";
    private final String expiresIn = "expires_in";
    private final String expiresAt = "expires_at";

    public TokenServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExternalTokenDto fetchTokenByRest(ExternalOrganizationEntity extOrgEntity) {
        try {
            Map<String, String> body = new HashMap<>();
            body.put("client_id", extOrgEntity.getClientId());
            body.put("client_secret", extOrgEntity.getClientSecret());
            body.put("username", extOrgEntity.getUsername());
            body.put("password", extOrgEntity.getPassword());
            body.put("grant_type", "password");

            ResponseEntity<Map> response = restTemplate.postForEntity(
                    extOrgEntity.getAuthUri(), body, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                String token = (String) response.getBody().get(accessToken);
                Object expiresInRes = response.getBody().get(expiresIn);
                Object expiresAtRes = response.getBody().get(expiresAt);

                Instant expiresAt = null;

                if (expiresInRes != null) {
                    Integer expiresIn = (Integer) expiresInRes;
                    expiresAt = DateTimeZoneUtil.DurationAndInstantUtils.calculateExpiry(
                            expiresIn, extOrgEntity.getTimeUnitType(), 50);
                } else if (expiresAtRes != null) {
                    String expiresAtStr = (String) expiresAtRes;
                    expiresAt = DateTimeZoneUtil.DurationAndInstantUtils.calculateExpiry(
                            Instant.parse(expiresAtStr), 50);
                }

                // اگر اطلاعات ناقص بود، هیچ توکنی برنگردونه
                if (token == null || expiresAt == null) {
                    log.error("Token یا Expiry زمان معتبر نیست.");
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
    public ExternalTokenDto fetchTokenByRest(ExternalOrganizationEntity extOrgEntity, HttpMethod method) {
        HttpEntity<Map<String, String>> httpEntity = extOrgEntity.getAuthType().getHttpEntity(extOrgEntity);
        ResponseEntity<Map> response = restTemplate.exchange(extOrgEntity.getAuthUri(), method, httpEntity, Map.class);
        return parseResponse(response,extOrgEntity.getTimeUnitType());
    }

    public ExternalTokenDto fetchTokenBySoap(ExternalOrganizationEntity extOrgEntity) {
        return new ExternalTokenDto(null, null, true, 0);
    }

    private ExternalTokenDto parseResponse(ResponseEntity<Map> response, TimeUnitType timeUnitType) {
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            String token = (String) response.getBody().get(accessToken);
            Object expiresInRes = response.getBody().get(expiresIn);
            Object expiresAtRes = response.getBody().get(expiresAt);

            Instant expiresAt = null;

            if (expiresInRes != null) {
                expiresAt = DateTimeZoneUtil.DurationAndInstantUtils.calculateExpiry(
                        (Integer) expiresInRes, timeUnitType, 50);
            } else if (expiresAtRes != null) {
                String expiresAtStr = (String) expiresAtRes;
                expiresAt = DateTimeZoneUtil.DurationAndInstantUtils.calculateExpiry(
                        Instant.parse(expiresAtStr), 50);
            }

            // اگر اطلاعات ناقص بود، هیچ توکنی برنگردونه
            if (token == null || expiresAt == null) {
                log.error("Token یا Expiry زمان معتبر نیست.");

                return ExternalTokenDto.builder()
                        .token(null)
                        .expiresAt(null)
                        .isValidToken(false).build();
            }

            log.info("GET TOKEN FROM SERVER - EXPIRE IN TIME : {} ", expiresAt);
            return new ExternalTokenDto(token, expiresAt, true, 0);
        }

        log.error("Token یا Expiry زمان معتبر نیست.");
        return ExternalTokenDto.builder().token(null).expiresAt(null).isValidToken(false).build();
    }


}
