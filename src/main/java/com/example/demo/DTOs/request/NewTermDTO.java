package com.example.demo.DTOs.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NewTermDTO {
    @Valid
    @NotEmpty
    List<RegisterNewTermDTO> registerNewTermDTOs;

    @Pattern(regexp = "^[0-9]+$")
    @NotBlank(message = "Trường ID đợt đăng ký không thể trống")
    String regSessID;
}
