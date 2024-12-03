package com.example.demoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandlersAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AgeCanNotBeLowerThanZero.class)
    public ResponseEntity<String> handleAgeCanNotBeLowerThanZeroException(AgeCanNotBeLowerThanZero e) {
        // log exception
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(UsersCanNotBeNullException.class)
    public ResponseEntity<String> handleUsersCanNotBeNullExceptionException(UsersCanNotBeNullException e) {
        // log exception
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

    @ExceptionHandler(IdsCanNotBeEmptyException.class)
    public ResponseEntity<String> IdsCanNotBeEmptyException(IdsCanNotBeEmptyException e) {
        // log exception
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }
}
