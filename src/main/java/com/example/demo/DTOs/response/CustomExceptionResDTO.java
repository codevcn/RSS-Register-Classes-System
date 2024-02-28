package com.example.demo.DTOs.response;

import com.example.demo.utils.constants.Names;
import com.example.demo.utils.exceptions.CustomAuthException;
import com.example.demo.utils.exceptions.CustomBaseException;
import org.springframework.http.HttpStatus;

public class CustomExceptionResDTO extends ExceptionResDTO {

    public CustomExceptionResDTO(CustomBaseException exception, String message) {
        setMessage(message);
        setTimestamp(exception.getTimestamp());

        if (exception instanceof CustomAuthException) {
            setName(Names.ExceptionNames.AUTH_EXCEPTION);
            setHttpStatus(HttpStatus.FORBIDDEN);
        } else {
            setName(Names.ExceptionNames.INTERNAL_SERVER_ERROR);
            setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
