package com.linkedin.social.controller;

import com.linkedin.social.dto.JwtResponse;
import com.linkedin.social.dto.LoginRequest;
import com.linkedin.social.dto.RegisterRequest;
import com.linkedin.social.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
