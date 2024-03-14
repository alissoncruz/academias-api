package com.core.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.core.model.AuthUserDTO;
import com.core.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public AuthUserDTO generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            var expiresAt = genExpirationDate();
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(expiresAt)
                    .sign(algorithm);

            return AuthUserDTO.builder()
                    .token(token)
                    .level(user.getLevel())
                    .expiresAt(String.valueOf(expiresAt.hashCode()))
                    .user(user.getLogin())
                    .build();
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return "";
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
