package com.example.demo.DTOs.request;

import jakarta.validation.Valid;
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
public class ScheduleDTO {
    @NotBlank(message = "Trường ngày bắt đầu lịch học không thể trống")
    String beginDate;

    @NotBlank(message = "Trường ngày kết thúc lịch học không thể trống")
    String endDate;

    @NotBlank(message = "Trường thứ trong tuần không thể trống")
    @Pattern(regexp = "^[0-9]+$")
    String dayOfWeek;

    @NotBlank(message = "Trường số tiết học không thể trống")
    @Pattern(regexp = "^[0-9]+$")
    String numberOfSessions;

    @NotBlank(message = "Trường phòng học không thể trống")
    String roomCode;

    @NotBlank(message = "Trường tổ không thể trống")
    String partGroup;

    @NotBlank(message = "Trường nhóm không thể trống")
    String teamGroup;

    @NotBlank(message = "Trường tiết bắt đầu không thể trống")
    @Pattern(regexp = "^[0-9]+$")
    String startingSession;

    @Pattern(regexp = "^[0-9]+$")
    @NotBlank(message = "Trường số slot không thể trống")
    String slotsCount;

    @Valid
    TeacherDTO teacher;

    @NotBlank(message = "Trường ID lớp học không thể trống")
    String classID;
}
