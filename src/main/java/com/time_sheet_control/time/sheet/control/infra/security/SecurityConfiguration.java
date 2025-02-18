package com.time_sheet_control.time.sheet.control.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        return httpSecurity
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
        .requestMatchers("/auth/login").permitAll()
        .requestMatchers("/auth/register").hasRole("ADMIN")
        .requestMatchers("/users/create").hasRole("ADMIN")
        .requestMatchers("/users/all").hasRole("ADMIN")
        .requestMatchers("/users/update/{id}").hasRole("ADMIN")
        .requestMatchers("/users/delete/{id}").hasRole("ADMIN")
        .requestMatchers("/users/user/{id}").authenticated()
        .anyRequest().authenticated()
        )
        .build();

    }
    
}
