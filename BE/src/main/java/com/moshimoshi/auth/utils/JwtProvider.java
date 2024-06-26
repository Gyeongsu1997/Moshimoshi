package com.moshimoshi.auth.utils;

import com.moshimoshi.auth.dto.TokenResponse;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(JwtProperties.class)
public class JwtProvider {
    private final JwtProperties jwtProperties;

    public TokenResponse createJwt(Map<String, Object> claims) {
        String accessToken = createToken(claims, getExpireDateAccessToken());
        String refreshToken = createToken(new HashMap<>(), getExpireDateRefreshToken());
        return TokenResponse.of(accessToken, refreshToken);
    }

    public String createToken(Map<String, Object> claims, Date expireDate) {
        return Jwts.builder()
                .claims(claims)
                .expiration(expireDate)
                .signWith(getSecretKey())
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    public Date getExpireDateAccessToken() {
        return new Date(System.currentTimeMillis() + jwtProperties.getAccessExp());
    }

    public Date getExpireDateRefreshToken() {
        return new Date(System.currentTimeMillis() + jwtProperties.getRefreshExp());
    }
}

