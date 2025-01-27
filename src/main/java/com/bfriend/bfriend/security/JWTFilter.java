package com.bfriend.bfriend.security;

import com.bfriend.bfriend.users.entity.Users;
import com.bfriend.bfriend.utils.constants.JWTConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = extractToken(request);
            Authentication authentication = createAuthenticationFromToken(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException ex) {
            SecurityContextHolder.clearContext();
            throw ex;
        }

        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String authorization= request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith(JWTConstants.TOKEN_PREFIX)) {
            throw new AuthenticationServiceException("Authorization 헤더가 소실되었거나 유효하지 않습니다.");
        }

        String[] parts = authorization.split(" ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new AuthenticationServiceException("토큰이 없거나 소실되었습니다.");
        }

        String token = parts[1];

        if (jwtUtil.isExpired(token)) {
            throw new AuthenticationServiceException("Token이 만료되었습니다.");
        }

        return token;
    }

    private Authentication createAuthenticationFromToken(String token) {
        String email = jwtUtil.getEmail(token);
        String role = jwtUtil.getRole(token);

        Users UserEntity = Users.builder()
                .email(email)
                .password("temppassword")
                .role(role)
                .build();

        CustomUserDetails customUserDetails = new CustomUserDetails(UserEntity);
        return new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().equals("/login") ||request.getRequestURI().equals("/auth/join") ||request.getRequestURI().startsWith("/h2-consoleb");
    }
}