package com.example.demo.core.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("postgresql")
public class PostgresqlProServiceImpl implements ProfilesService {
    @Override
    public String getProfileName() {
        return "profile By Database Postgresql ....";
    }
}