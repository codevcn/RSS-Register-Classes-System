package com.example.demo.utils;

// import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionCatcher {

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<String> handleAnyException(Exception exception) {
        System.out.printf("\n>>> run this Any Exception catcher \n");
        System.out.printf("\n>>> ( \n");
        System.out.print(exception);
        System.out.printf("\n>>> ) \n");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    // @ExceptionHandler({ DataAccessException.class })
    // public ResponseEntity<String> handleDataAccessException(DataAccessException exception) {
    //     System.out.printf("\n>>> run this DataAccessException catcher \n");
    //     System.out.printf("\n>>> ( \n");
    //     System.out.print(exception);
    //     System.out.printf("\n>>> ) \n");
    //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    // }

    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        System.out.printf("\n>>> run this RuntimeException catcher \n");
        System.out.printf("\n>>> ( \n");
        System.out.print(exception);
        System.out.printf("\n>>> ) \n");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }
}
