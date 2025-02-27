package com.time_sheet_control.time.sheet.control.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerated {
     public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123";  
        String encodedPassword = encoder.encode(rawPassword);  
        System.out.println("Senha Criptografada: " + encodedPassword);
    }
}
