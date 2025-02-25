package com.time_sheet_control.time.sheet.control.exceptions;

public class UserNotFoundException extends RuntimeException{
    
    public UserNotFoundException(String message) {
        super(message);
    }
}
