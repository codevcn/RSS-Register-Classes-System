package com.example.demo.DTOs.request;

import jakarta.validation.Valid;
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
public class AddRegisterSessionDTO {
    @Valid
    RegisterSessionDTO registerSessionDTO;

    @Valid
    @NotEmpty
    ScheduledSubjectDTO[] scheduledSubjectDTOs;
}
