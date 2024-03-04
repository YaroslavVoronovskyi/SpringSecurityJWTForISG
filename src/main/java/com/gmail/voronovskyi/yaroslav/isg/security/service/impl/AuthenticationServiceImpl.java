package com.gmail.voronovskyi.yaroslav.isg.security.service.impl;

import com.gmail.voronovskyi.yaroslav.isg.security.dao.request.SignInRequest;
import com.gmail.voronovskyi.yaroslav.isg.security.dao.request.SignUpRequest;
import com.gmail.voronovskyi.yaroslav.isg.security.dao.response.JwtAuthenticationResponse;
import com.gmail.voronovskyi.yaroslav.isg.security.model.Role;
import com.gmail.voronovskyi.yaroslav.isg.security.model.User;
import com.gmail.voronovskyi.yaroslav.isg.security.repository.UserRepository;
import com.gmail.voronovskyi.yaroslav.isg.security.service.AuthenticationService;
import com.gmail.voronovskyi.yaroslav.isg.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
