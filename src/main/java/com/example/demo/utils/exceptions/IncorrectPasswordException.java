package com.example.demo.utils.exceptions;

import java.time.LocalDateTime;

public class IncorrectPasswordException extends CustomBaseException{
    public IncorrectPasswordException(String message) {
        super(message);
            
    }
}
