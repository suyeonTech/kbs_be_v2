package com.bfriend.bfriend.security;

import com.bfriend.bfriend.users.entity.Users;
import com.bfriend.bfriend.utils.constants.JWTConstants;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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

        //Authorization 헤더 검증
        if (authorization == null || !authorization.startsWith(JWTConstants.TOKEN_PREFIX)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Authorization header missing or invalid\"}");
            return;
        }
        // Bearer 이후의 토큰 부분이 없는 경우
        String[] parts = authorization.split(" ");
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Token is missing or empty\"}");
            return;
        }

        String token = parts[1];

        if (jwtUtil.isExpired(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Token expired\"}");
            filterChain.doFilter(request, response);
            return;
        }

        String tokenEmail  = jwtUtil.getEmail(token);
        String role = jwtUtil.getRole(token);

        String requestEmail = request.getParameter("email");

        if (requestEmail != null && !tokenEmail.equals(requestEmail)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Token email does not match request email\"}");
            return;
        }

        Users userEntity = Users.builder()
                .email(tokenEmail)
                .password("temppassword")
                .role(role)
                .build();

        //UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);    // 세션이 생성되었기 때문에, 특정한 경로에 접근 가능해짐

        // 메소드 종료되었으므로 그 다음 필터에게 우리의 필터가 받았던 request와 response를 넘겨준다
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getRequestURI().equals("/login") ||request.getRequestURI().equals("/user/join") ||request.getRequestURI().startsWith("/h2-consoleb");
    }
}