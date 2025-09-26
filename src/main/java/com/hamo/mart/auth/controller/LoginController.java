package com.hamo.mart.auth.controller;

import com.hamo.mart.auth.dto.LoginRequest;
import com.hamo.mart.auth.dto.LoginResponse;
import com.hamo.mart.auth.dto.TokenResponse;
import com.hamo.mart.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        var tokenResponse = authService.login(loginRequest);
        return ResponseEntity.ok(tokenResponse);
    }

}
