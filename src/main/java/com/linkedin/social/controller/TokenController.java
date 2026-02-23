package com.linkedin.social.controller;

import com.linkedin.social.dto.RefreshTokenRequest;
import com.linkedin.social.dto.TokenRefreshResponse;
import com.linkedin.social.security.JwtService;
import com.linkedin.social.service2.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/token")
@RequiredArgsConstructor
public class TokenController {

    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    @PostMapping("/refresh")
    public TokenRefreshResponse refreshToken(
            @RequestBody RefreshTokenRequest request) {

        var refreshToken =
                refreshTokenService.verifyRefreshToken(
                        request.getRefreshToken());

        String accessToken =
                jwtService.generateTokenFromUsername(
                        refreshToken.getUser().getUsername());

        return new TokenRefreshResponse(
                accessToken,
                refreshToken.getToken()
        );
    }
}
