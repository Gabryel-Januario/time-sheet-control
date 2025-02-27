package com.time_sheet_control.time.sheet.control.controllers;


import java.security.Principal;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.time_sheet_control.time.sheet.control.models.timerecords.TimeRecords;
import com.time_sheet_control.time.sheet.control.service.TimeRecordsService;

@RestController
@RequestMapping("time")
public class TimeRecordsController {

    @Autowired
    private TimeRecordsService service;
    
    @PostMapping("/check_in")
    public ResponseEntity<String> checkIn(Principal principal) {
       Timestamp checkIn= this.service.checkIn(principal);

       return ResponseEntity.ok().body("Check In successfully performed. Time: " + checkIn);
    }

    @PostMapping("/check_out/{id}")
    public ResponseEntity<String> checkOut(@PathVariable String id, Principal principal) {
        TimeRecords timeRecords = this.service.checkOut(id, principal);

        return ResponseEntity.ok().body("Check Out successfully perfomed. TimeRecords: " + timeRecords);
    }
}
