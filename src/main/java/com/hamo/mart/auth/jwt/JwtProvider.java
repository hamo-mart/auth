package com.hamo.mart.auth.jwt;

import com.hamo.mart.auth.dto.LoginRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private Key signingKey;

    public JwtProvider(@Value("${jwt.secret}") String secret) {
        this.signingKey = Keys.hmacShaKeyFor(secret.getBytes());
    }



    public String generateAccessToken(LoginRequest request){
        String id = String.valueOf(request.getUserId());
        Claims claims = Jwts.claims().setSubject(id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000));
        claims.put("roles", request.getRoles());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(signingKey)
                .compact();
    }

    public String generateRefreshToken(LoginRequest request){
        String id = String.valueOf(request.getUserId());
        Claims claims = Jwts.claims().setSubject(id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 604800000));

        return Jwts.builder()
                .setClaims(claims)
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
