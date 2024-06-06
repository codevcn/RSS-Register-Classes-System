package com.example.demo.DTOs.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
public class StudentCancelRegisterDTO {
    @Pattern(regexp = "^[0-9]+$")
    @NotBlank(message = "Trường ID đợt đăng ký không thể trống")
    String regSessID;

    @NotEmpty(message = "Danh sách ID lịch học không thể trống")
    String[] scheduleIDs;
}
