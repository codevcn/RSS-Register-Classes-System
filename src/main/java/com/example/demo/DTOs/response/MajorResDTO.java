package com.example.demo.DTOs.response;

// import com.example.demo.DTOs.response.StudentResDTOs.MajorOfSubjectResDTO;

public class MajorResDTO {

    public static record GetMajorInfoResDTO(Long id, String majorCode, String name
    // Date updatedAt,
    // Long creditCount,
    // MajorOfSubjectResDTO major
    ) {
    }
}
