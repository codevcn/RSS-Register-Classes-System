package com.example.demo.DTOs.response;

import java.sql.Timestamp;
import java.util.List;

public class SubjectResDTO {

    public static record GetSubjectInfoResDTO(
        String id,
        String name,
        Long creditCount,
        MajorOfSubjectResDTO major
    ) {}

    public static record TeacherOfSubjectResDTO(String teacherID, String teacherName) {}

    public static record GetAllSubjectInfoResDTO(
        String id,
        String name,
        Timestamp updatedAt,
        Long creditCount,
        MajorOfSubjectResDTO major
    ) {}

    public static record MajorOfSubjectResDTO(String majorID, String majorName) {}

    public static record SubjectAndAllMajorsInfoDTO(
        GetAllSubjectInfoResDTO subjectInfo,
        List<MajorOfSubjectResDTO> allMajors
    ) {}

    public static record CreditOfSubjectResDTO(Long creditID) {}

    public static record CreateSubjectInfoResDTO(
        String id,
        String name,
        Timestamp createdAt,
        Timestamp updatedAt,
        Long creditCount,
        CreditOfSubjectResDTO credit,
        MajorOfSubjectResDTO major
    ) {}
}
