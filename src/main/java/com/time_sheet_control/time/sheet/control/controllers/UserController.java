package com.time_sheet_control.time.sheet.control.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @PostMapping("/create")
    public ResponseEntity<String> createUser() {
        return ResponseEntity.ok("User created successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<String> getAllUsers() {
        return ResponseEntity.ok("All users here");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<String> userById() {
        return ResponseEntity.ok("User by id here");
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> updatedUser(){
        return ResponseEntity.ok("User updated successfully");

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser() {
        return ResponseEntity.ok("User deleted successfully");
    }
}
