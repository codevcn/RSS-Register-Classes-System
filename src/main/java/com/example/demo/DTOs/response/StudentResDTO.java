package com.example.demo.DTOs.response;

import com.example.demo.models.Major;
import java.sql.Date;

public class StudentResDTO {

    public static record GetStudentInfoResDTO(
        Long id,
        String studentCode,
        String phone,
        String fullName,
        Date birthday,
        String idcard,
        String gender,
        Major major,
        Boolean deleted
        //Boolean deleted
        //String accountUsername,
        //String role
    ) {}
    // public static record MajorOfSubjectResDTO(
    //     String majorID,
    //     String majorName
    // ) {}

}
