package com.bfriend.bfriend.security.exception;

import com.bfriend.bfriend.utils.exceptions.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        CustomAuthenticationException customEx =
                (CustomAuthenticationException) request.getAttribute("CustomAuthException");

        ErrorResponse errorResponse;

        if (customEx != null) {
            var errorCode = customEx.getErrorCode();
            errorResponse = new ErrorResponse(
                    errorCode.getStatus(),
                    errorCode.getCode(),
                    errorCode.getMessage(),
                    null
            );
        } else {
            errorResponse = new ErrorResponse(
                    HttpStatus.UNAUTHORIZED,
                    "AUTHENTICATION_FAILED",
                    "Authentication failed",
                    null
            );
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        new ObjectMapper().writeValue(response.getWriter(), errorResponse);
    }
}