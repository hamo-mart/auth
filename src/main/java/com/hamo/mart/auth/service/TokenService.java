package com.hamo.mart.auth.service;

import com.hamo.mart.auth.dto.LoginRequest;
import com.hamo.mart.auth.dto.LoginResponse;

public interface TokenService {


    LoginResponse login(LoginRequest request);

    LoginResponse refreshToken(LoginRequest request, String clientRefreshToken);
}
