package com.bfriend.bfriend.utils.config;

import com.bfriend.bfriend.security.CustomUserDetailsService;
import com.bfriend.bfriend.security.JWTFilter;
import com.bfriend.bfriend.security.JWTUtil;
import com.bfriend.bfriend.security.LoginFilter;
import com.bfriend.bfriend.security.exception.CustomAuthenticationEntryPoint;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomUserDetailsService customUserDetailsService, PasswordEncoder passwordEncoder) throws Exception {

        http
                .cors((cors) -> cors
                        .configurationSource(new CorsConfigurationSource() {

                            @Override
                            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                                CorsConfiguration configuration = new CorsConfiguration();

                                configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                                configuration.setAllowedMethods(Collections.singletonList("*"));
                                configuration.setAllowCredentials(true);
                                configuration.setAllowedHeaders(Collections.singletonList("*"));
                                configuration.setMaxAge(3600L);

                                configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                                return configuration;
                            }
                        }));

        http.csrf(csrf -> csrf.disable());

        http.formLogin(form -> form.disable());

        http.httpBasic(basic -> basic.disable());

        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/", "/auth/join", "/h2-consoleb/**", "/health").permitAll()
                .requestMatchers("/users/test").permitAll() // ✅프론트 백 연동 테스트
                .requestMatchers("/admin").hasRole("USER")
                .anyRequest().authenticated()
        ).exceptionHandling(exception -> exception
                .authenticationEntryPoint(customAuthenticationEntryPoint) // Custom EntryPoint 등록
        );;

        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);


        http
                .addFilterAt(new LoginFilter(
                        authenticationManager(authenticationConfiguration),
                        jwtUtil
                ), UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .securityContext(securityContext -> securityContext
                        .requireExplicitSave(false));

        return http.build();
    }
}