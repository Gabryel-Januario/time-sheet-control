package com.time_sheet_control.time.sheet.control.models.dto.users;

import com.time_sheet_control.time.sheet.control.models.users.UserRole;

import lombok.Data;


@Data
public class UsersDTO {
    private String name;
    private String login;
    private String position;
    private UserRole role;


    public UsersDTO(String name, String login, String position, UserRole role) {
        this.name = name;
        this.login = login;
        this.position = position;
        this.role= role;
    }
}
