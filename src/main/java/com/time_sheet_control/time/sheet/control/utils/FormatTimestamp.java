package com.time_sheet_control.time.sheet.control.utils;



import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class FormatTimestamp {
    
    public String format(Timestamp timestamp) {

         LocalDateTime dateTime = timestamp.toLocalDateTime();

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        
        return dateTime.format(outputFormatter);
    }
}
