package com.time_sheet_control.time.sheet.control.controllers;


import java.security.Principal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.time_sheet_control.time.sheet.control.models.dto.timeRecordDTO.TimeRecordsResponseDTO;
import com.time_sheet_control.time.sheet.control.models.timerecords.TimeRecords;

import com.time_sheet_control.time.sheet.control.service.TimeRecordsService;

@RestController
@RequestMapping("time")
public class TimeRecordsController {

    @Autowired
    private TimeRecordsService service;
    
    @PostMapping("/check_in")
    public ResponseEntity<TimeRecords> checkIn(Principal principal) {
       TimeRecords timeRecords= this.service.checkIn(principal);

       return ResponseEntity.ok().body(timeRecords);
    }

    @PostMapping("/check_out/{id}")
    public ResponseEntity<TimeRecords> checkOut(@PathVariable String id, Principal principal) {
        TimeRecords timeRecord = this.service.checkOut(id, principal);

        return ResponseEntity.ok().body(timeRecord);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TimeRecordsResponseDTO>> getAll() {
        List<TimeRecordsResponseDTO> timeRecords = this.service.getAll();

        return ResponseEntity.status(HttpStatus.FOUND).body(timeRecords);
    }

    @GetMapping("/my")
    public ResponseEntity<List<TimeRecordsResponseDTO>> getMy(Principal principal) {
        List<TimeRecordsResponseDTO> timeRecords = this.service.getMy(principal);

        return ResponseEntity.ok().body(timeRecords);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<TimeRecordsResponseDTO>> getById(@PathVariable String id) {
        List<TimeRecordsResponseDTO> timeRecords = this.service.getById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(timeRecords);
    }
}
