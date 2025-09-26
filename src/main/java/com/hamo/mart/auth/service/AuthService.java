package com.hamo.mart.auth.service;

import com.hamo.mart.auth.dto.LoginRequest;
import com.hamo.mart.auth.dto.TokenRequest;
import com.hamo.mart.auth.dto.TokenResponse;

public interface AuthService {

    TokenResponse login(LoginRequest loginRequest);
}
