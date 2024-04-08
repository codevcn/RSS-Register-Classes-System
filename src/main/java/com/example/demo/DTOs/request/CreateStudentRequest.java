package com.example.demo.DTOs.request;

import com.example.demo.models.Major;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateStudentRequest {

    private String studentCode;
    private String fullName;
    private String gender;
    private String birthday;
    private String idcard;
    private String phone;
    private Major major;
    private String username;
    private String password;
}
