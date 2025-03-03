package com.time_sheet_control.time.sheet.control.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.time_sheet_control.time.sheet.control.infra.security.TokenService;
import com.time_sheet_control.time.sheet.control.models.dto.authDTO.LoginRequestDTO;
import com.time_sheet_control.time.sheet.control.models.dto.authDTO.LoginResponseDTO;
import com.time_sheet_control.time.sheet.control.models.dto.authDTO.RegisterDTO;
import com.time_sheet_control.time.sheet.control.models.users.User;
import com.time_sheet_control.time.sheet.control.service.AuthenticationService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationService service;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO data) {

        this.service.register(data);

        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");

    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO data) {
        Authentication auth = this.service.login(data);

        User user = this.service.getUser(auth);

        String token = this.tokenService.generateToken(user);

        LoginResponseDTO response = new LoginResponseDTO(token);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
