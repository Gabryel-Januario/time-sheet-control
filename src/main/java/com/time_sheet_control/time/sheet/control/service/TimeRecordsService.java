package com.time_sheet_control.time.sheet.control.service;

import java.math.BigDecimal;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.time_sheet_control.time.sheet.control.exceptions.TimeRecordsNotFoundException;
import com.time_sheet_control.time.sheet.control.models.dto.timeRecordDTO.TimeRecordsResponseDTO;
import com.time_sheet_control.time.sheet.control.models.timerecords.TimeRecords;
import com.time_sheet_control.time.sheet.control.models.users.User;
import com.time_sheet_control.time.sheet.control.repositories.TimeRecordsRepository;
import com.time_sheet_control.time.sheet.control.repositories.UserRepository;
import com.time_sheet_control.time.sheet.control.utils.FormatTimestamp;

@Service
public class TimeRecordsService {

    @Autowired
    private TimeRecordsRepository timeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FormatTimestamp formatTimestamp;

    public TimeRecords checkIn(Principal principal) {
        if (principal == null) {
            throw new IllegalArgumentException("User not authenticate");
        }
        
        Authentication authentication = (Authentication) principal;

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = (User) this.userRepository.findByLogin(userDetails.getUsername());
        
        String userId = user.getId();

        User userReference = new User();
        userReference.setId(userId);

        Timestamp checkIn =  Timestamp.from(Instant.now());

        TimeRecords newTimeRecords = new TimeRecords(userReference, checkIn);
        this.timeRepository.save(newTimeRecords);

        return newTimeRecords;
    }

    public TimeRecords checkOut(String timeRecordId, Principal principal) {
        if(principal == null) throw new IllegalArgumentException("User not authenticated");

        Authentication authentication = (Authentication) principal;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User)this.userRepository.findByLogin(userDetails.getUsername());

        TimeRecords timeRecord = this.timeRepository.findById(timeRecordId).
                            orElseThrow(() -> new TimeRecordsNotFoundException("TimeRecords not found."));
        
        if(!timeRecord.getUser().getId().equals(user.getId())) {
            throw new IllegalArgumentException("User does not have permission to change this time record");
        }             

        Timestamp checkOut = Timestamp.from(Instant.now());
        timeRecord.setCheckOut(checkOut);

        long diffMillis = timeRecord.getCheckOut().getTime() - timeRecord.getCheckIn().getTime();
        double convertedHours = diffMillis / (1000.0 * 60 * 60);
        BigDecimal hoursWorked = BigDecimal.valueOf(convertedHours);
        timeRecord.setHoursWorked(hoursWorked);

        timeRepository.save(timeRecord);
        return timeRecord;

    }

    public List<TimeRecordsResponseDTO> getAll() {
        List<TimeRecords> timeRecords = this.timeRepository.findAll();

        if(timeRecords.isEmpty()) throw new TimeRecordsNotFoundException("TimeRecords not found");


        return timeRecords.stream()
                .map(timeRecord ->
                 new TimeRecordsResponseDTO(
                    timeRecord.getId(), 
                    timeRecord.getUser().getId(),
                    formatTimestamp.format(timeRecord.getCheckIn()), 
                     formatTimestamp.format(timeRecord.getCheckOut()), 
                     timeRecord.getHoursWorked())).toList();
    }

    public List<TimeRecordsResponseDTO> getMy(Principal principal) {
        if(principal == null) {
            throw new IllegalArgumentException("User not authenticated");
        }

        Authentication authentication = (Authentication) principal;
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = (User) this.userRepository.findByLogin(userDetails.getUsername());

        List<TimeRecords> timeRecords = user.getTimeRecords();

        return timeRecords
        .stream().map(timeRecord -> new TimeRecordsResponseDTO(
            timeRecord.getId(), 
            timeRecord.getUser().getId(), 
            formatTimestamp.format(timeRecord.getCheckIn()), 
            formatTimestamp.format(timeRecord.getCheckOut()), 
            timeRecord.getHoursWorked())).toList();
    }

    public List<TimeRecordsResponseDTO> getById(String id){
        User user = this.userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<TimeRecords> timeRecords = user.getTimeRecords();

        return timeRecords
        .stream().map(timeRecord -> new TimeRecordsResponseDTO(
            timeRecord.getId(), 
            timeRecord.getUser().getId(), 
            formatTimestamp.format(timeRecord.getCheckIn()), 
            formatTimestamp.format(timeRecord.getCheckOut()), 
            timeRecord.getHoursWorked())).toList();
    }
}
