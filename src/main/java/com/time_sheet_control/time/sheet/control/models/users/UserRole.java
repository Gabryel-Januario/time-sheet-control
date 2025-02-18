package com.time_sheet_control.time.sheet.control.models.users;

public enum UserRole {
    
    ADMIN("admin"),
    USER("user");

    String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
