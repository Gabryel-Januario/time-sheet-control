package com.time_sheet_control.time.sheet.control.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.time_sheet_control.time.sheet.control.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{

    @Autowired
    private TokenService TokenService;

    @Autowired
    private UserRepository userRepository;

    private String recoverToken( HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null) return null;
        return authHeader.replace("Bearer ", ""); 
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException{
        String requestURI = request.getRequestURI();
    
        if (requestURI.equals("/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        

        var token = this.recoverToken(request);
        if(token != null) {
            try {
                var login = this.TokenService.validateToken(token);
                UserDetails user = this.userRepository.findByLogin(login);

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities() );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (RuntimeException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
                response.getWriter().write("Unauthorized: Invalid or expired token.");
                return;  
            }
        }else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  
            response.getWriter().write("Unauthorized: Token is missing.");
            return;  
        }
        filterChain.doFilter(request, response);
    }         
}
