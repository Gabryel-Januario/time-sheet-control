package com.time_sheet_control.time.sheet.control.infra.security;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.time_sheet_control.time.sheet.control.models.users.User;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret; 

    Date expiresAt = Date.from(Instant.now().plusSeconds(3600));

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                            .withIssuer("spring-security")
                            .withSubject(user.getLogin())
                            .withExpiresAt(expiresAt)
                            .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("spring-security")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error while verification token", exception);
        }
    }
}
