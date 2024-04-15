package com.example.demo.controllers;

import com.example.demo.DTOs.request.LoginReqDTO;
import com.example.demo.DTOs.response.AuthResDTO.AuthInfoResDTO;
import com.example.demo.DTOs.response.AuthResDTO.UserAuthInfoResDTO;
import com.example.demo.DTOs.response.AuthResDTO.UserInfoResDTO;
import com.example.demo.DTOs.response.SuccessResDTO;
import com.example.demo.models.Account;
import com.example.demo.models.Admin;
import com.example.demo.models.Student;
import com.example.demo.services.AccountService;
import com.example.demo.services.AdminService;
import com.example.demo.services.AuthService;
import com.example.demo.services.CookieService;
import com.example.demo.services.JWTService;
import com.example.demo.services.StudentService;
import com.example.demo.utils.constants.Roles.UserRoles;
import com.example.demo.utils.exceptions.CustomAuthException;
import com.example.demo.utils.messages.AuthMessage;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
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
    private AccountService accountService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudentService studentService;

    @PostMapping("login/student")
    public ResponseEntity<SuccessResDTO> loginStudent(
        @RequestBody @Valid LoginReqDTO loginDTO,
        @NonNull HttpServletResponse httpServletResponse
    ) throws CustomAuthException {
        Authentication authentication;
        loginDTO.setUsername(loginDTO.getUsername().trim());
        try {
            authentication = authService.loginStudent(loginDTO);
        } catch (Exception e) {
            throw new CustomAuthException(AuthMessage.WRONG_USERNAME_OR_PASSWORD);
        }
        if (
            authentication
                .getAuthorities()
                .iterator()
                .next()
                .getAuthority()
                .equals(UserRoles.ROLE_STUDENT.getValue()) ==
            false
        ) {
            throw new CustomAuthException(AuthMessage.BAD_AUTHORITY);
        }
        String jwt = jwtService.generateToken(loginDTO.getUsername());
        cookieService.setCookieAtClient(httpServletResponse, jwt);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResDTO(true));
    }

    @PostMapping("login/admin")
    public ResponseEntity<SuccessResDTO> loginAdmin(
        @RequestBody @Valid LoginReqDTO loginDTO,
        @NonNull HttpServletResponse httpServletResponse
    ) throws CustomAuthException {
        Authentication authentication;
        loginDTO.setUsername(loginDTO.getUsername().trim());
        try {
            authentication = authService.loginAdmin(loginDTO);
        } catch (Exception e) {
            throw new CustomAuthException(AuthMessage.WRONG_USERNAME_OR_PASSWORD);
        }
        if (
            authentication
                .getAuthorities()
                .iterator()
                .next()
                .getAuthority()
                .equals(UserRoles.ROLE_ADMIN.getValue()) ==
            false
        ) {
            throw new CustomAuthException(AuthMessage.BAD_AUTHORITY);
        }
        String jwt = jwtService.generateToken(loginDTO.getUsername());
        cookieService.setCookieAtClient(httpServletResponse, jwt);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResDTO(true));
    }

    @GetMapping("check-auth")
    public ResponseEntity<UserAuthInfoResDTO> getMethodName(Principal principal)
        throws CustomAuthException {
        Account account = accountService.getAccountInfo(principal.getName());
        UserAuthInfoResDTO userAuthInfoResDTO;
        if (
            account.getRole().getRoleCode().toUpperCase().equals(UserRoles.ROLE_STUDENT.getValue())
        ) {
            Student studentInfo = studentService.findStudentByAccountID(account.getId());
            userAuthInfoResDTO = new UserAuthInfoResDTO(
                new AuthInfoResDTO(account.getRole().getRoleCode()),
                new UserInfoResDTO(studentInfo.getFullName())
            );
        } else if (
            account.getRole().getRoleCode().toUpperCase().equals(UserRoles.ROLE_ADMIN.getValue())
        ) {
            Admin adminInfo = adminService.findAdminByAccountID(account.getId());
            userAuthInfoResDTO = new UserAuthInfoResDTO(
                new AuthInfoResDTO(account.getRole().getRoleCode()),
                new UserInfoResDTO(adminInfo.getFullName())
            );
        } else {
            throw new CustomAuthException(AuthMessage.BAD_AUTHORITY);
        }

        return ResponseEntity.ok(userAuthInfoResDTO);
    }
}
