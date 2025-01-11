package com.bfriend.bfriend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests
                                .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
                                .anyRequest().permitAll() // Requires authentication for other requests

                                authorizeHttpRequests
                                        .anyRequest().permitAll()

                )
                .csrf((csrfConfig) ->
                        csrfConfig.disable()) // Disable CSRF protection for H2 console (optional)
                .headers((headerConfig) ->
                        headerConfig.frameOptions(frameOptionsConfig ->
                                frameOptionsConfig.disable()
                        )); // Allow frames for H2 console
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}