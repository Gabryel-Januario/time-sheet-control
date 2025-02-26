package com.time_sheet_control.time.sheet.control.controllers;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.time_sheet_control.time.sheet.control.service.TimeRecordsService;

@RestController
@RequestMapping("time")
public class TimeRecordsController {

    @Autowired
    private TimeRecordsService service;
    
    @PostMapping("/check_in")
    public ResponseEntity<String> checkIn(Principal principal) {
       String userId = this.service.checkIn(principal);

       return ResponseEntity.ok().body("User_id: " + userId);
    }

    @PostMapping("/check_out")
    public void checkOut() {

    }
}
