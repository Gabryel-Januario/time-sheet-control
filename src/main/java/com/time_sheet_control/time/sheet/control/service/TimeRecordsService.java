package com.time_sheet_control.time.sheet.control.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.time_sheet_control.time.sheet.control.models.users.User;
import com.time_sheet_control.time.sheet.control.repositories.TimeRecordsRepository;
import com.time_sheet_control.time.sheet.control.repositories.UserRepository;

@Service
public class TimeRecordsService {

    @Autowired
    private TimeRecordsRepository timeRepository;

    @Autowired
    private UserRepository userRepository;

    public String checkIn(Principal principal) {
        if (principal == null) {
            throw new IllegalArgumentException("User not authenticate");
        }
        
        Authentication authentication = (Authentication) principal;

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        User user = (User) this.userRepository.findByLogin(userDetails.getUsername());
        
        String userId = user.getId();

        return userId;
    }
}
