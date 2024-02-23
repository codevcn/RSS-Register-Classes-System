package com.example.demo.configs;

import com.example.demo.utils.constants.Lengths;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionCatcher {

    private String specifyErrorMessage(String error_message) {
        int maxLengthOfErrorMessage = Lengths.maxLengthOfErrorMessage;
        return error_message.length() > maxLengthOfErrorMessage
            ? error_message.substring(0, maxLengthOfErrorMessage) + "..."
            : error_message;
    }

    private void logException(String exception_title, Exception exception) {
        System.out.printf("\n>>> run this " + exception_title + " catcher");
        System.out.printf("\n>>> :::::::::::::::::::::( \n");
        System.out.print(exception);
        System.out.printf("\n>>> :::::::::::::::::::::) \n");
    }

    private void logValidationException(MethodArgumentNotValidException exception) {
        System.out.printf("\n>>> run this Validation Exception catcher\n");
        exception
            .getBindingResult()
            .getAllErrors()
            .forEach(error -> {
                System.out.printf(">>> error message: %s \n", error.getDefaultMessage());
            });
    }

    // handle other exceptions
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<String> handleAnyException(Exception exception) {
        logException("Any Exception", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    // validation exception for @Valid
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<String> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException exception,
        Model model
    ) {
        logValidationException(exception);
        String errorMessage = specifyErrorMessage(
            "Dữ liệu đầu vào không hợp lệ: " +
            exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    // catch sql query exceptions
    @ExceptionHandler({ DataAccessException.class })
    public ResponseEntity<String> handleDataAccessException(DataAccessException exception) {
        logException("DataAccess Exception", exception);
        String errorMessage = specifyErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    // catch exceptions in runtime
    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        logException("Runtime Exception", exception);
        String errorMessage = specifyErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
