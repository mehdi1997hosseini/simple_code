package com.example.demo.core.thirdParty.externalOrganization.token;

import com.example.demo.core.thirdParty.externalOrganization.dto.ExternalOrganizationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
    private final RestTemplate restTemplate;

    public TokenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExternalTokenDto fetchToken(ExternalOrganizationDto org) {
        Map<String, String> body = new HashMap<>();
        body.put("client_id", org.getClientId());
        body.put("client_secret", org.getClientSecret());
        body.put("username", org.getUsername());
        body.put("password", org.getPassword());
        body.put("grant_type", "password");

        ResponseEntity<Map> response = restTemplate.postForEntity(
                org.getAuthUrl(), body, Map.class);

        String token = (String) response.getBody().get("access_token");
        Integer expiresIn = (Integer) response.getBody().get("expires_in");

        Instant expiresAt = Instant.now().plusSeconds(expiresIn - 30); // 60s buffer

        return new ExternalTokenDto(token, expiresAt);
    }

}
