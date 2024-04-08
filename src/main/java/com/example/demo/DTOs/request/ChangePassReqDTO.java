package com.example.demo.DTOs.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class ChangePassReqDTO {

    @NotBlank(message = "Trường tên tài khoản không thể thiếu!")
    private String oldPass;

    @NotBlank(message = "Trường mật khẩu không thể thiếu")
    private String newPass;

    @NotEmpty(message = "Trường mật khẩu không thể thiếu")
    private Long id;
}
