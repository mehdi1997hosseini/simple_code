package com.example.demo.core.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("h2")
@Service
public class H2ProServiceImpl implements ProfilesService {
    @Override
    public String getProfileName() {
        return "profile By Database H2 ....";
    }
}