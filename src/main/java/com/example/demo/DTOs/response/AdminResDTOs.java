package com.example.demo.DTOs.response;

import java.sql.Date;

public class AdminResDTOs {
    public static record GetAdminInfoResDTO(
        String idcard,
        String fullName,
        Date birthday,
        String gender,
        String accountUsername,
        String role
    ) {}
}

