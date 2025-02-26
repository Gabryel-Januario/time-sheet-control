package com.time_sheet_control.time.sheet.control.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.time_sheet_control.time.sheet.control.exceptions.UserNotFoundException;
import com.time_sheet_control.time.sheet.control.models.dto.users.UserRequestDTO;
import com.time_sheet_control.time.sheet.control.models.dto.users.UserResponseDTO;
import com.time_sheet_control.time.sheet.control.models.dto.users.UsersDTO;
import com.time_sheet_control.time.sheet.control.models.users.User;
import com.time_sheet_control.time.sheet.control.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UsersDTO> getAll() {
        List<User> users = this.repository.findAll();

        if(users.isEmpty()) throw new UserNotFoundException("Users not found");

        return users.stream().map(user -> new UsersDTO(user.getName(), user.getLogin(), user.getPosition(), user.getRole())).toList();
    }

    public UserResponseDTO getById(String id) {
        User user = this.repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        UserResponseDTO response = new UserResponseDTO(user.getName(), user.getLogin(), user.getPosition(), user.getRole());

        return response;

    }

    public void updateUser(String id, UserRequestDTO data) {

        User user = this.repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));

        if(data.name() != null && !data.name().isEmpty() ) {
            user.setName(data.name());
        }
        if(data.login() != null && !data.login().isEmpty() ) {
            user.setLogin(data.login());;
        }
        if(data.password() != null && !data.password().isEmpty() ) {
            user.setPassword(passwordEncoder.encode(data.password()));;
        }
        if(data.position() != null && !data.position().isEmpty() ) {
            user.setPosition(data.position());;
        }
        if(data.role() != null ) {
            user.setRole(data.role());;
        }

        this.repository.save(user);

 
    }

    public void deleteUser(String id) {
        User user = this.repository.findById(id).orElseThrow(() -> new UserNotFoundException("User with id: " + id + " not found"));

        this.repository.delete(user);

    }
}
