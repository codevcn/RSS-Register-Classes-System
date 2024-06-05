package com.example.demo.DTOs.response;

public class StudentClassResDTO {

    public static record GetStudentClassInfoResDTO(
        Long id,
        String code,
        String name
        //int majorID
        // Date updatedAt,
        // Long creditCount,
        // MajorOfSubjectResDTO major
    ) {}
    
}
