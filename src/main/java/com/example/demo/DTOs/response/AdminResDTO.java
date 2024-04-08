package com.example.demo.DTOs.response;

import java.sql.Date;

import com.example.demo.models.Account;

public class AdminResDTO {

    public static record GetAdminAccountInfoResDTO(
        Integer id,
        String idcard,
        String fullName,
        Date birthday,
        String gender,
        Account account
    ) {}

    public static record GetAdminInfoResDTO(
        Long id,
        String idcard,
        String fullName,
        Date birthday,
        String gender,
        Account account
    ) {}
}
