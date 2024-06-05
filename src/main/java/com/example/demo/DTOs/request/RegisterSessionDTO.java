package com.example.demo.DTOs.request;

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
public class RegisterSessionDTO {
    @NotBlank(message = "Trường mã đợt đăng ký không thể trống")
    String regSessCode;

    @NotBlank(message = "Trường thời gian bắt đầu đợt đăng ký không thể trống")
    String beginTime;

    @NotBlank(message = "Trường thời gian kết thúc đợt đăng ký không thể trống")
    String endTime;
}
