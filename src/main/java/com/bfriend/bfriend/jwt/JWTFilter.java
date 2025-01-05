package com.bfriend.bfriend.jwt;

import com.bfriend.bfriend.users.dto.response.CustomUserDetails;
import com.bfriend.bfriend.users.entity.Users;
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
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Authorization header missing or invalid\"}");
            return;
        }

        String token = authorization.split(" ")[1];

        if (jwtUtil.isExpired(token)) { // 토큰이 만료된 상태인 경우
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"Token expired\"}");
            filterChain.doFilter(request, response);
            return;
        }

        String tokenEmail  = jwtUtil.getEmail(token);
        String role = jwtUtil.getRole(token);

        String requestEmail = request.getParameter("email"); // 요청에서 이메일 추출
        System.out.println("tokenEmail: " + tokenEmail);
        System.out.println("requestEmail: " + requestEmail);

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
        return request.getRequestURI().equals("/login")|| request.getRequestURI().equals("/join");
    }
}