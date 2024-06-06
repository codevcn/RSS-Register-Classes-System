package com.example.demo.DTOs.request;

import jakarta.validation.Valid;
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
public class ScheduledSubjectDTO {
    @Valid
    ScheduleDTO schedule;

    @Valid
    SubjectDTO subject;
}
