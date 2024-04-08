package com.example.demo.DTOs.response;

import java.sql.Date;
import java.sql.Timestamp;

import com.example.demo.models.Role;

public class AccountResDTO {
        public static record GetAccountInfoResDTO(
                        Long id,
                        String username,
                        String fullName,
                        String password,
                        Timestamp createdAt,
                        Timestamp updatedAt,
                        Boolean deleted,
                        Role role) {
        }

        public static record GetAccountResDTO(
                        String username,
                        String password,
                        Timestamp updatedAt) {
        }
        public static record GetAllusernameResDTO(
        Long id,        
        String username
                ) {
}
public static record PassResDTO(
        String oldPass,        
        String newPass
                ) {
}
public static record getAllIdcard(
        String idCard
                ) {
}

}
