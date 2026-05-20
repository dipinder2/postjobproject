package com.example.demo.config;

import com.example.demo.security.JWTMiddleware;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                                "/api/v1/twilio/otp/send",
                                "/api/v1/twilio/otp/verify")
                        .permitAll()
                        .requestMatchers("api/v1/jobs/**","api/v1/users/**")
                        .authenticated()
                        .anyRequest()
                        .permitAll())
                .addFilterBefore(
                        new JWTMiddleware(),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}