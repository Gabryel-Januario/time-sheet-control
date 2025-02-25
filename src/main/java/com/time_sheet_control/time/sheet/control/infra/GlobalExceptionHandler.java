package com.time_sheet_control.time.sheet.control.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.time_sheet_control.time.sheet.control.exceptions.ElementNotFound;
import com.time_sheet_control.time.sheet.control.exceptions.UserAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> HandleUserAlreadyExistsException(UserAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(ElementNotFound.class)
    public ResponseEntity<String> HandlerElementNotFound(ElementNotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
