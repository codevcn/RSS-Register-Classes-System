package com.example.demo.configs;

import com.example.demo.DTOs.response.CustomExceptionResDTO;
import com.example.demo.DTOs.response.ExceptionResDTO;
import com.example.demo.utils.constants.Lengths;
import com.example.demo.utils.exceptions.CustomBaseException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

class Helpers {

    public static String specifyErrorMessage(String error_message) {
        int maxLengthOfErrorMessage = Lengths.maxLengthOfErrorMessage;
        return error_message.length() > maxLengthOfErrorMessage
            ? error_message.substring(0, maxLengthOfErrorMessage) + "..."
            : error_message;
    }

    public static void logException(String exception_title, Exception exception) {
        System.out.printf("\n>>> run this " + exception_title + " catcher");
        System.out.printf("\n>>> :::::::::::::::::::::( \n");
        System.out.print(exception);
        System.out.printf("\n>>> :::::::::::::::::::::) \n");
    }

    public static void logValidationException(MethodArgumentNotValidException exception) {
        System.out.printf("\n>>> run this Validation Exception catcher\n");
        exception
            .getBindingResult()
            .getAllErrors()
            .forEach(error -> {
                System.out.printf(">>> error message: %s \n", error.getDefaultMessage());
            });
    }

    public static void printStackTrace(Exception exception) {
        StackTraceElement stackTraceElement = exception.getStackTrace()[0];
        System.out.printf(
            ">>> StackTrace [Class: %s, CodeLine: %s,  Method: %s] \n\n",
            stackTraceElement.getClassName(),
            stackTraceElement.getLineNumber(),
            stackTraceElement.getMethodName()
        );
    }
}

@ControllerAdvice
public class ExceptionCatcher {

    // handle other exceptions
    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ExceptionResDTO> handleAnyException(Exception exception) {
        Helpers.logException("Any Exception", exception);
        Helpers.printStackTrace(exception);

        ExceptionResDTO resBody = new ExceptionResDTO(exception, exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resBody);
    }

    // validation exception for @Valid
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<ExceptionResDTO> handleMethodArgumentNotValidException(
        MethodArgumentNotValidException exception,
        Model model
    ) {
        Helpers.logValidationException(exception);
        Helpers.printStackTrace(exception);

        String errorMessage = Helpers.specifyErrorMessage(
            "Dữ liệu đầu vào không hợp lệ: " +
            exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()
        );

        ExceptionResDTO resBody = new ExceptionResDTO(exception, errorMessage);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resBody);
    }

    // catch sql query exceptions
    @ExceptionHandler({ DataAccessException.class })
    public ResponseEntity<ExceptionResDTO> handleDataAccessException(DataAccessException exception) {
        Helpers.logException("DataAccess Exception", exception);
        Helpers.printStackTrace(exception);

        String errorMessage = Helpers.specifyErrorMessage(exception.getMessage());

        ExceptionResDTO resBody = new ExceptionResDTO(exception, errorMessage);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resBody);
    }

    // catch exceptions in runtime
    @ExceptionHandler({ RuntimeException.class })
    public ResponseEntity<ExceptionResDTO> handleRuntimeException(RuntimeException exception) {
        Helpers.logException("Runtime Exception", exception);
        Helpers.printStackTrace(exception);

        String errorMessage = Helpers.specifyErrorMessage(exception.getMessage());

        ExceptionResDTO resBody = new ExceptionResDTO(exception, errorMessage);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resBody);
    }

    // catch base exceptions
    @ExceptionHandler({ CustomBaseException.class })
    public ResponseEntity<ExceptionResDTO> handleBaseException(CustomBaseException exception) {
        Helpers.logException("Custom Base Exception", exception);
        Helpers.printStackTrace(exception);

        String errorMessage = Helpers.specifyErrorMessage(exception.getMessage());

        CustomExceptionResDTO resBody = new CustomExceptionResDTO(exception, errorMessage);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resBody);
    }
}
