package com.hamo.mart.auth.adapter;


import com.hamo.mart.auth.dto.LoginRequest;
import com.hamo.mart.auth.dto.LoginResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "${feign.client.gateway.url}")
public interface UserAdapter {

    @PostMapping("/api/auth/login")
    LoginResponse login(@RequestBody LoginRequest loginRequest);
}
