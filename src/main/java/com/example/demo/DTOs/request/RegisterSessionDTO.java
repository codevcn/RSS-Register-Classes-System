package com.example.demo.DTOs.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class RegisterSessionDTO {
    @NotBlank(message = "Trường mã đợt đăng ký không thể trống")
    String regSessCode;

    @NotBlank(message = "Trường thời gian bắt đầu đợt đăng ký không thể trống")
    String beginTime;

    @NotBlank(message = "Trường thời gian kết thúc đợt đăng ký không thể trống")
    String endTime;

    @NotBlank(message = "Trường mã học kì không thể trống")
    String termCode;

    @NotBlank(message = "Trường ID ngành học không thể trống")
    @Pattern(regexp = "^[0-9]+$")
    String majorID;
}
