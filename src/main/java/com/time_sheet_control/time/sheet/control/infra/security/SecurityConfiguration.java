package com.time_sheet_control.time.sheet.control.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager; 
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                                        .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                                        
                                        .requestMatchers(HttpMethod.GET,"/users/user/{id}").authenticated()
                                        .requestMatchers(HttpMethod.GET, "/record/my").authenticated()

                                        .requestMatchers(HttpMethod.POST, "/auth/register").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET,"/users/all").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.PUT,"/users/user/{id}").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE,"/users/user/{id}").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET, "/record/all").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET, "/record/employee/{id}").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET, "/record/employee/{id}/pdf").hasRole("ADMIN")
                                        .anyRequest().authenticated()
                                        
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(auth -> auth.successHandler(new OAuth2LoginSuccessHandler()));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
