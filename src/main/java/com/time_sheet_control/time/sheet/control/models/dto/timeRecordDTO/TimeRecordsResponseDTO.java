package com.time_sheet_control.time.sheet.control.models.dto.timeRecordDTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TimeRecordsResponseDTO {
    private String id;
    private String userId;
    private String checkIn;
    private String checkOut;
    private BigDecimal hoursWorked;

    public TimeRecordsResponseDTO(String id, String userId, String checkIn, String checkOut, BigDecimal hoursWorked) {
        this.id = id;
        this.userId = userId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.hoursWorked = hoursWorked;
    }
}   
