package com.time_sheet_control.time.sheet.control.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
