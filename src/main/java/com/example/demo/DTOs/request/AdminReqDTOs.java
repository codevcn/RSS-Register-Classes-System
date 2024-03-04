package com.example.demo.DTOs.request;

import java.sql.Date;

public class AdminReqDTOs {
    public static record CreateAdminReqDTO(
        String idcard,
        String fullName,
        Date birthday,
        String gender,
        String accountUsername,
        String role
    ) {}
}
