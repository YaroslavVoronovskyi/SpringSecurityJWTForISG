package com.gmail.voronovskyi.yaroslav.isg.security.service;

import com.gmail.voronovskyi.yaroslav.isg.security.dto.request.SignInRequest;
import com.gmail.voronovskyi.yaroslav.isg.security.dto.request.SignUpRequest;
import com.gmail.voronovskyi.yaroslav.isg.security.dto.response.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);
}
