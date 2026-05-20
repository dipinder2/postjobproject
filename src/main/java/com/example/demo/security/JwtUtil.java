package com.example.demo.security;

import io.github.cdimascio.dotenv.Dotenv;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final Dotenv dotenv = Dotenv.load();

    private static final String SECRET =
            dotenv.get("JWT_SECRET");

    private static final Key KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    // GENERATE TOKEN
    public static String generateToken(String phone) {

        return Jwts.builder()
                .setSubject(phone)
                .claim("phone", phone)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 86400000)
                )
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // VALIDATE TOKEN
    public static Claims validateToken(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}