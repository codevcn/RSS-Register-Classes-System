package com.example.demo.DTOs.response;

import java.sql.Date;
import java.sql.Timestamp;

public class AdminResDTOs {
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
        Integer id,    
        String idcard,
        String fullName,
        Date birthday,
        String gender,
        String accountUsername,
        String role
    ) {}
}

