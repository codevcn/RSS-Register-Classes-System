package com.example.demo.DTOs.response;

import com.example.demo.utils.constants.Names;
import com.example.demo.utils.exceptions.CustomAuthException;
import com.example.demo.utils.exceptions.CustomBaseException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CustomExceptionResDTO extends ExceptionResDTO {

    private String name;
    private int httpStatus;

    public CustomExceptionResDTO(CustomBaseException exception, String message) {
        setMessage(message);
        setTimestamp(exception.getTimestamp());

        if (exception instanceof CustomAuthException) {
            setName(Names.ExceptionNames.AUTH_EXCEPTION);
            setHttpStatus(HttpStatus.UNAUTHORIZED.value());
        } else {
            setName(Names.ExceptionNames.BAD_REQUEST);
            setHttpStatus(HttpStatus.BAD_REQUEST.value());
        }
    }
}
