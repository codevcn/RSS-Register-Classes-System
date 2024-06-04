package com.example.demo.DTOs.response;

import com.example.demo.models.Major;
import com.example.demo.models.StudentClass;
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
        StudentClass studentClass,
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
