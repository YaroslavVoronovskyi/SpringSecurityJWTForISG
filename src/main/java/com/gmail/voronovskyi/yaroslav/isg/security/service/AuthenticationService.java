package com.gmail.voronovskyi.yaroslav.isg.security.service;

import com.gmail.voronovskyi.yaroslav.isg.security.dao.request.SignInRequest;
import com.gmail.voronovskyi.yaroslav.isg.security.dao.request.SignUpRequest;
import com.gmail.voronovskyi.yaroslav.isg.security.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {

    JwtAuthenticationResponse signUp(SignUpRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);
}
