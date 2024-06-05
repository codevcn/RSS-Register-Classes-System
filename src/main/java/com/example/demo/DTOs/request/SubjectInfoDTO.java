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
public class SubjectInfoDTO {
    @NotBlank(message = "Trường tổ không thể trống")
    String partGroup;

    @NotBlank(message = "Trường nhóm không thể trống")
    String teamGroup;
}
