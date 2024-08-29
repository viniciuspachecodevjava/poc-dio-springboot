package com.poc.diospringboot.infra.security.implementacao;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.poc.diospringboot.domain.user.User;
import com.poc.diospringboot.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenServiceImp implements TokenService {
    @Value("${api.security.token.secret}")
    private String SECRET_KEY;

    @Override
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            String token = JWT.create()
                    .withIssuer("dio-springboot") // Quem solicita a criação do Token
                    .withSubject(user.getEmail()) // Quem recebe o Token
                    .withExpiresAt(this.generateExpirationDate()) // Data de expiração do Token
                    .sign(algorithm); // Algoritmo de criptografica contendo a senha para criação de Token
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while authenticating");
        }
    }
@Override
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer("dio-springboot") // Quem solicita a criação do Token
                    .build() // Monta o objeto
                    .verify(token)// Passa o token gerado
                    .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of(String.valueOf(-3)));
    }
}
