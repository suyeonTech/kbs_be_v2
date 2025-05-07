package com.bfriend.bfriend.utils.jwt.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");

        String message;

        if (authException.getMessage().contains("Authorization header missing or invalid")) {
            message = "Authorization header missing or invalid";
        } else if (authException.getMessage().contains("Token is missing or empty")) {
            message = "Token is missing or empty";
        } else if (authException.getMessage().contains("Token is expired")) {
            message = "Token has expired";
        } else {
            message = "Authentication failed";
        }

        Map<String, Object> errorResponse = Map.of(
                "status", 401,
                "error", "AUTHENTICATION_FAILED",
                "message", message
        );

        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    }
}