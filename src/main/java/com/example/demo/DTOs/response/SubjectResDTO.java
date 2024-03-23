package com.example.demo.DTOs.response;

import com.example.demo.models.Major;
import java.sql.Timestamp;

public class SubjectResDTO {

    public static record GetSubjectInfoResDTO(
        String id,
        String name,
        Long creditCount,
        MajorOfSubjectResDTO major
    ) {}

    public static record GetAllSubjectInfoResDTO(
        String id,
        String name,
        Timestamp updatedAt,
        Long creditCount,
        MajorOfSubjectResDTO major
    ) {}

    public static record MajorOfSubjectResDTO(String majorID, String majorName) {}

    public static record CreateSubjectInfoResDTO(
        Long id,
        String name,
        Long creditCount,
        Timestamp createdAt,
        Timestamp updatedAt,
        Major major
    ) {}

    public static record GetSubjectResDTO(
        Long id,
        String name,
        Long creditCount,
        Timestamp createdAt,
        Timestamp updatedAt,
        Major major,
        Boolean deleted
    ) {}
}
