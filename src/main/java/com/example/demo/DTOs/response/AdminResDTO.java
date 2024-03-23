package com.example.demo.DTOs.response;

import java.sql.Date;

public class AdminResDTO {

    public static record GetAdminAccountInfoResDTO(
        Integer id,
        String idcard,
        String fullName,
        Date birthday,
        String gender,
        String accountUsername,
        String role
    ) {}

    public static record GetAdminInfoResDTO(
        Long id,
        String idcard,
        String fullName,
        Date birthday,
        String gender,
        String accountUsername,
        String role
    ) {}
}
