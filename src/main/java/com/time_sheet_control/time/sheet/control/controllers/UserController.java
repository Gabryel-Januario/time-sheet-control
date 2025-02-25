package com.time_sheet_control.time.sheet.control.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.time_sheet_control.time.sheet.control.models.dto.users.UserResponseDTO;
import com.time_sheet_control.time.sheet.control.models.dto.users.UsersDTO;
import com.time_sheet_control.time.sheet.control.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService service;
     
    @GetMapping("/all")
    public ResponseEntity<List<UsersDTO>> getAllUsers() {

        List<UsersDTO> users = this.service.getAll();

        return ResponseEntity.status(HttpStatus.FOUND).body(users);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDTO> userById(@PathVariable String id ) {

        UserResponseDTO user = this.service.getById(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(user);
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
