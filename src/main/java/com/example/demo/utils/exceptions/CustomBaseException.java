package com.example.demo.utils.exceptions;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class CustomBaseException extends Exception {

    private LocalDateTime timestamp;

    public CustomBaseException(String message) {
        super(message);
        this.timestamp = LocalDateTime.now();
    }
}
