package com.example.demo.core.thirdParty.externalOrganization.token.service;

import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationEntity;
import com.example.demo.core.thirdParty.externalOrganization.ExternalOrganizationName;
import com.example.demo.core.thirdParty.externalOrganization.token.ExternalTokenDto;
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
        Map<String, String> body = new HashMap<>();
        body.put("client_id", extOrgEntity.getClientId());
        body.put("client_secret", extOrgEntity.getClientSecret());
        body.put("username", extOrgEntity.getUsername());
        body.put("password", extOrgEntity.getPassword());
        body.put("grant_type", "password");

        ResponseEntity<Map> response = restTemplate.postForEntity(
                extOrgEntity.getAuthUrl(), body, Map.class);

        String token = (String) response.getBody().get("access_token");
        Integer expiresIn = (Integer) response.getBody().get("expires_in");

        Instant expiresAt = Instant.now().plusMillis(expiresIn - 60000);

        return new ExternalTokenDto(token, expiresAt);
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

        return new ExternalTokenDto(token, expiresAt);
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
