package com.time_sheet_control.time.sheet.control.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.time_sheet_control.time.sheet.control.exceptions.UserNotFoundException;
import com.time_sheet_control.time.sheet.control.exceptions.TimeRecordsNotFoundException;
import com.time_sheet_control.time.sheet.control.exceptions.UserAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> HandleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> HandlerElementNotFound(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(TimeRecordsNotFoundException.class)
    public ResponseEntity<String> HandlerTimeRecordsNotFoundException(TimeRecordsNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
