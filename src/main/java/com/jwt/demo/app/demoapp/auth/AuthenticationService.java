package com.jwt.demo.app.demoapp.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwt.demo.app.demoapp.config.JWTService;
import com.jwt.demo.app.demoapp.entities.*;
import com.jwt.demo.app.demoapp.repository.UserDao;

import lombok.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserDao userDao;

    private final PasswordEncoder passwordEncoder;

    private final JWTService service;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request)
    {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .pass(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
                userDao.save(user);

        var token = service.generateToken(user);
        return AuthenticationResponse.builder()
        .token(token)
        .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        var user = userDao.findByEmail(request.getEmail())
            .orElseThrow();

        var token = service.generateToken(user);
        return AuthenticationResponse.builder()
        .token(token)
        .build();
    }
}
