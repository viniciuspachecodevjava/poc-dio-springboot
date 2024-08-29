package com.poc.diospringboot.infra.security;

import com.poc.diospringboot.domain.user.User;

import java.time.Instant;

public interface TokenService {
    public String generateToken(User user);
    public String validateToken(String token);
    private Instant generateExpirationDate() {
        return null;
    }
}
