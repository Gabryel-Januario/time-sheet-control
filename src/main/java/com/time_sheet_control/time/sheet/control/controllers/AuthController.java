package com.time_sheet_control.time.sheet.control.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.time_sheet_control.time.sheet.control.models.dto.authDTO.LoginDTO;
import com.time_sheet_control.time.sheet.control.models.dto.authDTO.RegisterDTO;

import com.time_sheet_control.time.sheet.control.service.AuthenticationService;

@RestController
@RequestMapping("auth")
public class AuthController {
    
    @Autowired
    private AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO data) {

        this.service.register(data);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO data) {
        this.service.login(data);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("User login successfully");
    }
}
