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
public class StudentClassDTO {
    @Pattern(regexp = "^[0-9]+$")
    @NotBlank(message = "Trường ID lớp học không thể trống")
    String id;
}
