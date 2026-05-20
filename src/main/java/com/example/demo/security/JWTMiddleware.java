package com.example.demo.security;

import io.jsonwebtoken.Claims;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JWTMiddleware extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String path = request.getServletPath();

        // PUBLIC ROUTES
        if (
                path.equals("/api/v1/twilio/otp/send") ||
                path.equals("/api/v1/twilio/otp/verify")
        ) {

            filterChain.doFilter(request, response);
            return;
        }

        String authHeader =
                request.getHeader("Authorization");

        if (
                authHeader == null ||
                !authHeader.startsWith("Bearer ")
        ) {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Missing JWT Token");

            return;
        }

        try {

            String token = authHeader.substring(7);

            Claims claims =
                    JwtUtil.validateToken(token);

            String phone =
                    claims.getSubject();

            // IMPORTANT PART
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            phone,
                            null,
                            List.of(new SimpleGrantedAuthority("USER"))
                    );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

        } catch (Exception e) {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid JWT Token");

            return;
        }

        filterChain.doFilter(request, response);
    }
}