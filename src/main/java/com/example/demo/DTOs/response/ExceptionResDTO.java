package com.example.demo.DTOs.response;

import com.example.demo.utils.constants.Names;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResDTO {

    private String name;
    private String message;
    private LocalDateTime timestamp;
    private HttpStatus httpStatus;

    public ExceptionResDTO(Exception exception, String message) {
        setMessage(message);
        setTimestamp(LocalDateTime.now());
        setName(Names.ExceptionNames.INTERNAL_SERVER_ERROR);
        setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
