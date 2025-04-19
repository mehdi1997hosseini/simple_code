package com.example.demo.core.thirdParty.externalOrganization.token.service;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;
import com.example.demo.core.thirdParty.externalOrganization.token.ExternalTokenDto;
import com.example.demo.core.utility.DateTimeZoneUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {
    private final RestTemplate restTemplate;

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
                    extOrgEntity.getAuthUrl(), body, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                String token = (String) response.getBody().get("access_token");
                Object expiresInRes = response.getBody().get("expires_in");
                Object expiresAtRes = response.getBody().get("expires_at");

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

                // اگر اطلاعات ناقص بود، هیچ توکنی برنگردون
                if (token == null || expiresAt == null) {
//                    throw new IllegalStateException("Token یا Expiry زمان معتبر نیست.");
                    return null;
                }

                return new ExternalTokenDto(token, expiresAt, true, 0);
            } else {
//                throw new IllegalStateException("پاسخ نامعتبر از سرور دریافت شد.");
                return null;
            }
        } catch (Exception ex) {
            // لاگ کن که بتونی بررسی کنی
//            ex.printStackTrace();
//            throw new RuntimeException("خطا در دریافت توکن از سرور مقصد: " + extOrgEntity.getOrgName(), ex);
            return null;
        }
    }

    public ExternalTokenDto fetchTokenBySoap(ExternalOrganizationEntity extOrgEntity) {
        Map<String, String> body = new HashMap<>();
        body.put("client_id", extOrgEntity.getClientId());
        body.put("client_secret", extOrgEntity.getClientSecret());
        body.put("username", extOrgEntity.getUsername());
        body.put("password", extOrgEntity.getPassword());
        body.put("grant_type", "password");

        System.out.println(body.toString());

        ResponseEntity<Map> response = restTemplate.postForEntity(
                extOrgEntity.getAuthUrl(), body, Map.class);

        System.out.println(response.toString());

        String token = (String) response.getBody().get("access_token");
        Integer expiresIn = (Integer) response.getBody().get("expires_in");

        Instant expiresAt = Instant.now().plusSeconds(expiresIn - 30); // 60s buffer

        return new ExternalTokenDto(token, expiresAt, true, 0);
    }

    public ExternalTokenDto getToken(ExternalOrganizationName extOrgName) {
//        ExternalTokenDto tokenDto = TokenCache.getToken(extOrgName);
//
//        if (tokenDto == null) {
//
//        }
//
//        return TokenCache.getToken(extOrgName);
        return null;
    }


}
