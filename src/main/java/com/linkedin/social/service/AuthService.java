
package com.linkedin.social.service;

import com.linkedin.social.dto.JwtResponse;
import com.linkedin.social.dto.LoginRequest;
import com.linkedin.social.dto.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);
    JwtResponse login(LoginRequest request);
}
