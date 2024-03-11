package com.example.demo.DTOs.response;

import java.sql.Date;
import com.example.demo.models.Major;

public class StudentResDTOs {

    public static record GetStudentInfoResDTO(
        String id,
        String phone,
        String fullName,
        Date birthday,
        String gender,
        String major
        //String accountUsername,
        //String role
    ) {}
    
}
