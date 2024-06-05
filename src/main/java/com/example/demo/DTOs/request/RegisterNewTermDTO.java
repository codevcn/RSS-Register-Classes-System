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
public class RegisterNewTermDTO {
    @NotBlank(message = "Trường ID lịch học không thể trống")
    String scheduleID;

    @NotBlank(message = "Trường ID môn học không thể trống")
    String subjectID;
}
