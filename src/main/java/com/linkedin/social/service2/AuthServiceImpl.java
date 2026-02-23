package com.linkedin.social.service2;

import com.linkedin.social.dto.JwtResponse;
import com.linkedin.social.dto.LoginRequest;
import com.linkedin.social.dto.RegisterRequest;
import com.linkedin.social.entity.Roles;
import com.linkedin.social.entity.Users;
import com.linkedin.social.repository.RolesRepository;
import com.linkedin.social.repository.UsersRepository;
import com.linkedin.social.security.JwtService;
import com.linkedin.social.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    private final RefreshTokenService refreshTokenService;

    @Override
    public String register(RegisterRequest request) {

        if (usersRepository.existsByUsername(request.getUsername())) {
            return "User already exists";
        }

        if (request.getRole() == null || request.getRole().isBlank()) {
            throw new RuntimeException("Role must be provided");
        }

        Roles role = rolesRepository.findByName(request.getRole())
                .orElseThrow(() ->
                        new RuntimeException("Role not found: " + request.getRole())
                );

        Users users = Users.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(role))
                .build();

        usersRepository.save(users);

        return "User registered successfully with role " + request.getRole();
    }


    @Override
    public JwtResponse login(LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getUsername(),
                                request.getPassword()
                        )
                );
        System.out.println(authentication + "bk");

        UserDetails userDetails =
                (UserDetails) authentication.getPrincipal();

        String accessToken = jwtService.generateToken(userDetails);

        String refreshToken =
                refreshTokenService.createRefreshToken(
                        userDetails.getUsername()
                ).getToken();

        Users user = usersRepository
                .findByUsername(userDetails.getUsername())
                .orElseThrow();

        String role = user.getRoles()
                .stream()
                .findFirst()
                .map(Roles::getName)
                .orElse("ROLE_USER");

        return new JwtResponse(accessToken
                ,refreshToken,
                role

        );
    }
}
