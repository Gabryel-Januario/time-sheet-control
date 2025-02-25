package com.time_sheet_control.time.sheet.control.models.dto.users;

import com.time_sheet_control.time.sheet.control.models.users.UserRole;

public record UserRequestDTO(String name, String login, String password, String position, UserRole role) {
    
}
