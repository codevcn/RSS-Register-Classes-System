package com.example.demo.DTOs.response;

import com.example.demo.utils.constants.Names;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResDTO {

    private String name = Names.ExceptionNames.INTERNAL_SERVER_ERROR;
    private String message;
    private LocalDateTime timestamp;

    @NonNull
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public ExceptionResDTO(Exception exception, String message) {
        setMessage(message);
        setTimestamp(LocalDateTime.now());
    }
}
