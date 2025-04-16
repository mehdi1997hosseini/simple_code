package com.example.demo.app.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequestTest implements Serializable {
    private String client_id;
    private String client_secret;
    private String username;
    private String password;
    private String grant_type;

}
