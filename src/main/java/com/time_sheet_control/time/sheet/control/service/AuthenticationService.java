package com.time_sheet_control.time.sheet.control.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import com.time_sheet_control.time.sheet.control.exceptions.UserAlreadyExistsException;
import com.time_sheet_control.time.sheet.control.models.dto.authDTO.LoginDTO;
import com.time_sheet_control.time.sheet.control.models.dto.authDTO.RegisterDTO;
import com.time_sheet_control.time.sheet.control.models.users.User;
import com.time_sheet_control.time.sheet.control.repositories.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {
    
    @Autowired
    private UserRepository repository;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(@Lazy AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return this.repository.findByLogin(username);
    }

    public User register(RegisterDTO data) {
        if(this.repository.findByLogin(data.login()) != null) {
            throw new UserAlreadyExistsException("User Already Exists with this email!");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User newUser = new User(data.name(), data.login(), encryptedPassword, data.position(), data.role());

        this.repository.save(newUser);

        return newUser;
    }

    public Authentication login(LoginDTO data) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
        
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        return auth;
    }

    public User getUser(Authentication auth) {
        User user = (User) auth.getPrincipal();

        return user;
    }
    
}
