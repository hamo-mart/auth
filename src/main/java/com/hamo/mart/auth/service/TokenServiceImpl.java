package com.hamo.mart.auth.service;

import com.hamo.mart.auth.dto.TokenRequest;
import com.hamo.mart.auth.dto.TokenResponse;
import com.hamo.mart.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private static final String REFRESH_TOKEN_PREFIX = "REFRESH:";
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60; // 7일

    private final JwtProvider jwtProvider;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public TokenResponse login(TokenRequest request) {
        String accessToken = jwtProvider.generateAccessToken(request);
        String refreshToken = jwtProvider.generateRefreshToken(request);

        String refreshTokenKey = REFRESH_TOKEN_PREFIX + request.getUserId();

        // TTL을 걸어서 Redis에 저장
        redisTemplate.opsForValue().set(refreshTokenKey, refreshToken, REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);

        return new TokenResponse(accessToken, refreshToken);
    }

    @Override
    public TokenResponse refreshToken(TokenRequest request, String clientRefreshToken) {
        String refreshTokenKey = REFRESH_TOKEN_PREFIX + request.getUserId();
        String storedRefreshToken = redisTemplate.opsForValue().get(refreshTokenKey);

        // refresh token 검증
        if (storedRefreshToken != null && storedRefreshToken.equals(clientRefreshToken)) {
            // refresh 토큰 자체도 유효한지 체크
            if (!jwtProvider.validateToken(storedRefreshToken)) {
                throw new RuntimeException("Refresh token is expired or invalid");
            }

            // 새 access token 발급
            String newAccessToken = jwtProvider.generateAccessToken(request);

            return new TokenResponse(newAccessToken, storedRefreshToken);
        }
        throw new RuntimeException("Invalid refresh token");
    }
}

