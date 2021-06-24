package com.juno.eats.api.domain.login;

import lombok.Getter;

@Getter
public class LoginResult {
    private String token;

    public LoginResult(String token) {
        this.token = token;
    }
}
