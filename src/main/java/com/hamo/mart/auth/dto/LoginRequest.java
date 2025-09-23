package com.hamo.mart.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoginRequest {

    private Long userId;
    private List<String> roles;
}
