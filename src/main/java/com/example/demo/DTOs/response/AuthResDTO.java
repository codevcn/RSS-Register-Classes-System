package com.example.demo.DTOs.response;

public class AuthResDTO {

    public static record UserAuthInfoResDTO(AuthInfoResDTO accountInfo, UserInfoResDTO userInfo) {}

    public static record AuthInfoResDTO(String role) {}

    public static record UserInfoResDTO(String nameOfUser) {}

    public static record UnauthenticatedUserDTO(String message) {}
}
