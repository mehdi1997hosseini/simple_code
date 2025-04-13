package com.example.demo.core.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("oracle")
public class OracleProServiceImpl implements ProfilesService {
    @Override
    public String getProfileName() {
        return "profile By Database Oracle ....";
    }
}