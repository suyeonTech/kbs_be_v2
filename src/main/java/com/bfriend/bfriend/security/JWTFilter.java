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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorization= request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith(JWTConstants.TOKEN_PREFIX)) {
            throw new AuthenticationServiceException("Authorization header missing or invalid");
        }

        String[] parts = authorization.split(" ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new AuthenticationServiceException("Token is missing or empty");
        }

        String token = parts[1];

        if (jwtUtil.isExpired(token)) {
            throw new AuthenticationServiceException("Token expired");
        }

        String tokenEmail  = jwtUtil.getEmail(token);
        String role = jwtUtil.getRole(token);

        String requestEmail = request.getParameter("email");

        if (requestEmail != null && !tokenEmail.equals(requestEmail)) {
            throw new AuthenticationServiceException("Token email does not match request email");
        }

        Users userEntity = Users.builder()
                .email(tokenEmail)
                .password("temppassword")
                .role(role)
                .build();

        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);    // 세션이 생성되었기 때문에, 특정한 경로에 접근 가능해짐

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().equals("/login") ||request.getRequestURI().equals("/auth/join") ||request.getRequestURI().startsWith("/h2-consoleb");
    }
}