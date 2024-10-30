package com.ekeberg.spring_rest_api_sql_ai_travel.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
public class JwtUtil {


    // Use a secure key - Base64 (openssl rand -base64 32)
    private static final String SECRET_KEY = "9ROAvjxP+CoxyXQYoy4p2qbmKbId0LWJT5gW4nKFFFY=";

    private static Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Generate a token with subject as the user email
    public static String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10-hour expiration
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // Validate the token against the user's email
    public static boolean validateToken(String token, String userEmail) {
        String tokenEmail = extractEmail(token);
        return (tokenEmail.equals(userEmail) && !isTokenExpired(token));
    }

    // Extract email (subject) from the token
    public static String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    // Check if token has expired
    private static boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    // Retrieve claims from token
    private static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
