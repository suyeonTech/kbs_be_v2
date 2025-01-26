package com.bfriend.bfriend.security;

import com.bfriend.bfriend.utils.constants.JWTConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Map;

@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> credentials = objectMapper.readValue(req.getInputStream(), Map.class);

            String email = credentials.get("email");
            String password = credentials.get("password");

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (IOException ex) {
            throw new RuntimeException("요청 본문 파싱에 실패했습니다.", ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication authentication) {
        try {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

            String email = customUserDetails.getUsername();
            String role = customUserDetails.getAuthorities().iterator().next().getAuthority();

            String token = jwtUtil.createJwt(email, role, JWTConstants.TOKEN_VALIDITY_MILLISECONDS_1HOUR);

            res.addHeader("Authorization", JWTConstants.TOKEN_PREFIX + token);

            res.setStatus(HttpServletResponse.SC_OK);
            res.setContentType("application/json;charset=UTF-8");
            res.getWriter().write("\"로그인에 성공하셨습니다.\"");

        } catch (IOException ex) {
            throw new RuntimeException("응답 작성 실패", ex);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Map<String, Object> errorResponse = Map.of(
                "status", 401,
                "error", "INVALID_CREDENTIALS",
                "message", "이메일 또는 비밀번호가 잘못되었습니다."
        );
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse));
    }
}