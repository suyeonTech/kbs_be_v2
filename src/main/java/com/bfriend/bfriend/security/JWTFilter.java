package com.bfriend.bfriend.security;

import com.bfriend.bfriend.security.exception.CustomAuthenticationException;
import com.bfriend.bfriend.users.entity.Users;
import com.bfriend.bfriend.utils.constants.JWTConstants;
import com.bfriend.bfriend.utils.exceptions.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final RedisTemplate<String, String> redisTemplate;
    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = extractToken(request);

            String isLogout = redisTemplate.opsForValue().get(token);
            if ("logout".equals(isLogout)) {
                throw new CustomAuthenticationException(ErrorCode.LOGOUT_TOKEN_USED);
            }
            Authentication authentication = createAuthenticationFromToken(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (CustomAuthenticationException ex) {
            SecurityContextHolder.clearContext();
            request.setAttribute("CustomAuthException", ex);
            throw new InsufficientAuthenticationException(ex.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String authorization= request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith(JWTConstants.TOKEN_PREFIX)) {
            throw new CustomAuthenticationException(ErrorCode.AUTHENTICATION_HEADER_MISSING);
        }

        String[] parts = authorization.split(" ", 2);
        if (parts.length < 2 || parts[1] == null || parts[1].isBlank()) {
            throw new CustomAuthenticationException(ErrorCode.AUTHENTICATION_HEADER_MISSING);
        }

        String token = parts[1].trim();

        if (jwtUtil.isExpired(token)) {
            throw new CustomAuthenticationException(ErrorCode.TOKEN_EXPIRED);
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
        Authentication authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        System.out.println("Request URI: " + request.getRequestURI());
        return request.getRequestURI().equals("/login")
                ||request.getRequestURI().equals("/auth/join")
                ||request.getRequestURI().startsWith("/h2-consoleb")
                ||request.getRequestURI().equals("/favicon.ico")
                ||request.getRequestURI().equals("/health")
                ||request.getRequestURI().equals("/users/checkemail")
                ||request.getRequestURI().equals("/users/findpw")
                ||request.getRequestURI().equals("/users/changepw")
                || request.getRequestURI().equals("/users/test"); // ✅프론트 백 연동 테스트
    }
}