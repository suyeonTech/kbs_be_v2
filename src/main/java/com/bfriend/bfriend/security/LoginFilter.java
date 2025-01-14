package com.bfriend.bfriend.security;
import com.bfriend.bfriend.utils.constants.JWTConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

//    @Override
//    protected String obtainUsername(HttpServletRequest request) {
//        return request.getParameter("email");
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
//
//        String email = obtainUsername(req);
//        String password = obtainPassword(req);
//
//        System.out.println("Attempting login with email: " + email + ", password: " + password);
//
//        // 이메일로 사용자 로드
//        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(email);
//        System.out.println("User loaded: " + userDetails.getUsername());
//
//        // 비밀번호 검증
//        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
//            System.out.println("Password does not match for user: " + email);
//            throw new BadCredentialsException("Invalid username or password");
//        }
//
//        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, password, null);
//
//        return authenticationManager.authenticate(authToken);
//    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {

        String email = null;
        String password = null;

        try {
            // JSON 요청 데이터를 읽고 파싱
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = req.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            String requestBody = sb.toString();
            System.out.println("Request Body: " + requestBody);

            // ObjectMapper를 사용하여 JSON 데이터 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> requestMap = objectMapper.readValue(requestBody, Map.class);

            email = requestMap.get("email");
            password = requestMap.get("password");

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Attempting login with email: " + email + ", password: " + password);

        // 이메일로 사용자 로드
        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(email);
        System.out.println("User loaded: " + userDetails.getUsername());

        // 비밀번호 검증
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            System.out.println("Password does not match for user: " + email);
            throw new BadCredentialsException("Invalid username or password");
        }

        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(email, password, userDetails.getAuthorities());

        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        String email = customUserDetails.getUsername();
        String role = customUserDetails.getAuthorities().iterator().next().getAuthority();

        String token = jwtUtil.createJwt(email, role, JWTConstants.TOKEN_VALIDITY_MILLISECONDS_1HOUR);

        response.addHeader("Authorization", JWTConstants.TOKEN_PREFIX + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(401);
    }
}