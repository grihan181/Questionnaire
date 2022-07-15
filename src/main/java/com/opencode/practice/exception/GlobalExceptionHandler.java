package com.opencode.practice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionData> handleExeption(NoSuchCountExeption exeption) {
        ExceptionData exceptionData = new ExceptionData();
        exceptionData.setInfo(exeption.getMessage());
        return new ResponseEntity<>(exceptionData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ExceptionData> handleExeption(Exception exception) {
        ExceptionData exceptionData = new ExceptionData();
        exceptionData.setInfo(exception.getMessage());
        return new ResponseEntity<>(exceptionData, HttpStatus.BAD_REQUEST);
    }
}
