package com.example.demo.DTOs.response;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResDTO {

    private String message;
    private LocalDateTime timestamp;

    public ExceptionResDTO(String message) {
        setMessage(message);
        setTimestamp(LocalDateTime.now());
    }
}
