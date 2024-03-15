package com.example.demo.controllers;

import com.example.demo.DTOs.request.LoginReqDTO;
import com.example.demo.DTOs.response.SuccessResDTO;
import com.example.demo.configs.props.JWTProps;
import com.example.demo.services.AuthService;
import com.example.demo.services.CookieService;
import com.example.demo.services.JWTService;
import com.example.demo.utils.exceptions.CustomAuthException;
import com.example.demo.utils.messages.AuthMessage;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private CookieService cookieService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private JWTProps jwtProps;

    @PostMapping("login")
    public ResponseEntity<SuccessResDTO> login(
        @RequestBody @Valid LoginReqDTO loginDTO,
        @NonNull HttpServletResponse httpServletResponse
    ) throws CustomAuthException {
        try {
            authService.login(loginDTO);
        } catch (Exception e) {
            throw new CustomAuthException(AuthMessage.WRONG_USERNAME_OR_PASSWORD);
        }
        String jwt = jwtService.generateToken(loginDTO.getUsername());
        cookieService.setCookieAtClient(httpServletResponse, jwt);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResDTO(true));
    }
}
