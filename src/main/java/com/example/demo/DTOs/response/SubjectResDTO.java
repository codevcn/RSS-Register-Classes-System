package com.example.demo.DTOs.response;

import java.sql.Date;

public class SubjectResDTO {
    public static record GetSubjectInfoResDTO(
        String id,
        String name,
        Long creditCount,
        MajorOfSubjectResDTO major
    ) {}
    public static record MajorOfSubjectResDTO(
        String majorID,
        String majorName

    ) {
    }
}
