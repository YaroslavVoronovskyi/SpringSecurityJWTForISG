package com.gmail.voronovskyi.yaroslav.isg.security.controller;

import com.gmail.voronovskyi.yaroslav.isg.security.dao.request.SignInRequest;
import com.gmail.voronovskyi.yaroslav.isg.security.dao.request.SignUpRequest;
import com.gmail.voronovskyi.yaroslav.isg.security.dao.response.JwtAuthenticationResponse;
import com.gmail.voronovskyi.yaroslav.isg.security.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authenticationService.signUp(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authenticationService.signIn(request));
    }
}
