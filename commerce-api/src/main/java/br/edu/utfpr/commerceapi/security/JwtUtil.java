package br.edu.utfpr.commerceapi.security;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtil {
    public String generateToken(Map<String, Object> payloadClaims, String secret, long seconds)
            throws IllegalArgumentException, JWTCreationException {
        var now = Instant.now().atZone(ZoneId.of("UTC"));
        return this.generateToken(payloadClaims, secret, now.plus(seconds, ChronoUnit.SECONDS), now);
    }

    public String generateToken(Map<String, Object> payloadClaims, String secret, ZonedDateTime expiresAt)
            throws IllegalArgumentException, JWTCreationException {
        var now = Instant.now().atZone(ZoneId.of("UTC"));
        return this.generateToken(payloadClaims, secret, expiresAt, now);
    }

    public String generateToken(Map<String, Object> payloadClaims, String secret, ZonedDateTime expiresAt,
            ZonedDateTime issuedAt) throws IllegalArgumentException, JWTCreationException {
        return JWT.create() //
                .withSubject("API authentication") //
                .withPayload(payloadClaims) //
                .withIssuedAt(Date.from(issuedAt.toInstant())) //
                .withExpiresAt(Date.from(expiresAt.toInstant())) //
                .withIssuer("UTFPR") //
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveSubject(String token, String secret) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).withSubject("API authentication")
                .withIssuer("UTFPR").build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("username").asString();
    }
}