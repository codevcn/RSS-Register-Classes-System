package com.example.demo.services;

import com.example.demo.DTOs.request.LoginReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    public Authentication login(LoginReqDTO loginDTO) throws AuthenticationException {
        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken.unauthenticated(
                loginDTO.getUsername(),
                loginDTO.getPassword()
            )
        );
    }
}
