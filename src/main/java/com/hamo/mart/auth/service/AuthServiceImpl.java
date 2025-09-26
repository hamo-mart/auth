package com.hamo.mart.auth.service;

import com.hamo.mart.auth.adapter.UserAdapter;
import com.hamo.mart.auth.dto.LoginRequest;
import com.hamo.mart.auth.dto.LoginResponse;
import com.hamo.mart.auth.dto.TokenRequest;
import com.hamo.mart.auth.dto.TokenResponse;
import com.hamo.mart.auth.exception.UserNotAuthenticationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenService tokenService;
    private final UserAdapter userAdapter;

    @Override
    public TokenResponse login(LoginRequest loginRequest) {

        //TODO 유저 서버와 통신
        LoginResponse login = userAdapter.login(loginRequest);
        if (login != null) {
            return tokenService.login(new TokenRequest(login.getUserId(), login.getRoles(), login.getNickname()));
        }

        throw new UserNotAuthenticationException();
    }
}
