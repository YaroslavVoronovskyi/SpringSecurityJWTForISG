package com.gmail.voronovskyi.yaroslav.isg.security.service.impl;

import com.gmail.voronovskyi.yaroslav.isg.security.repository.UserRepository;
import com.gmail.voronovskyi.yaroslav.isg.security.service.UserService;
import com.gmail.voronovskyi.yaroslav.isg.security.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(Constants.NOT_FOUND_USER_MESSAGE));
    }
}
