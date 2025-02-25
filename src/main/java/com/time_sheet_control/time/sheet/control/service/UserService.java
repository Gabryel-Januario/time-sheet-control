package com.time_sheet_control.time.sheet.control.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.time_sheet_control.time.sheet.control.exceptions.ElementNotFound;
import com.time_sheet_control.time.sheet.control.models.dto.users.UsersDTO;
import com.time_sheet_control.time.sheet.control.models.users.User;
import com.time_sheet_control.time.sheet.control.repositories.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    public List<UsersDTO> getAll() {
        List<User> users = this.repository.findAll();

        if(users.isEmpty()) throw new ElementNotFound("Users not found");

        return users.stream().map(user -> new UsersDTO(user.getName(), user.getLogin(), user.getPosition(), user.getRole())).toList();
    }

}
