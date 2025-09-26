package com.hamo.mart.auth.service;

import com.hamo.mart.auth.dto.TokenRequest;
import com.hamo.mart.auth.dto.TokenResponse;

public interface TokenService {


    TokenResponse login(TokenRequest request);

    TokenResponse refreshToken(TokenRequest request, String clientRefreshToken);
}
