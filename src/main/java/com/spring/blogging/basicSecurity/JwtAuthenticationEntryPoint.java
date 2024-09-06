package com.spring.blogging.basicSecurity;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // You can log the exception for debugging purposes if needed
        System.out.println("Unauthorized error: " + authException.getMessage());

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid User!");
    }
}
