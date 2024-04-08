package com.example.demo.DTOs.request;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminReqDTO {

    @NotBlank(message = "Trường tên tài khoản không thể thiếu!")
    private String username;

    @NotBlank(message = "Trường căn cước công dân không thể thiếu!")
    private String idcard;

    @NotBlank(message = "Trường họ và tên không thể thiếu!")
    String fullName;

    @NotBlank(message = "Trường ngày sinh không thể thiếu!")
    Date birthday;

    @NotBlank(message = "Trường giới tính không thể thiếu!")
    String gender;
    
}
