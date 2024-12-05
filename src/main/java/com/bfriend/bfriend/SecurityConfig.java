package com.bfriend.bfriend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorizeHttpRequests) ->
                                authorizeHttpRequests
                                        .anyRequest().permitAll()
                        //.requestMatchers(new AntPathRequestMatcher("/h2-consoleb/**")).permitAll()
                        //.anyRequest().authenticated() // Requires authentication for other requests
                )
                .csrf((csrfConfig) ->
                        csrfConfig.disable()) // Disable CSRF protection for H2 console (optional)
                .headers((headerConfig) ->
                        headerConfig.frameOptions(frameOptionsConfig ->
                                frameOptionsConfig.disable()
                        )); // Allow frames for H2 console

        return http.build();
    }
}