package com.example.demo.DTOs.response;

public class AccountResDTO {
    
    public static record GetAccountResDTO(
        Integer id,
        String username,
        String password
    ) {}
}
