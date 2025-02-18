package com.time_sheet_control.time.sheet.control.models.dto.authDTO;

import com.time_sheet_control.time.sheet.control.models.users.UserRole;

public record RegisterDTO(String name, String login, String password, String position, UserRole role) {
    
}
