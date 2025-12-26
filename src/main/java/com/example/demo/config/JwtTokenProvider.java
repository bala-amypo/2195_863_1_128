package com.example.demo.config;

import com.example.demo.entity.UserAccount;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final Key key;
    private final long jwtExpirationInMs;

    public JwtTokenProvider() {
        // Default constructor for Spring
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        this.jwtExpirationInMs = 3600000;
    }

    // Constructor for testing with specific key and expiration
    public JwtTokenProvider(String secret, long jwtExpirationInMs) {
        // Ensure secret is long enough for HS512 if used, or use it to seed
        // For simplicity in this test context, we'll assume it's a raw string we turn into bytes
        // But the test provides a 32+ char string.
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.jwtExpirationInMs = jwtExpirationInMs;
    }

    public String generateToken(Authentication authentication, UserAccount user) {
        // In the test, authentication.getName() returns the username
        // We also want to include the user ID in the subject or claims?
        // The test checks "containsUserId", normally subject is username. 
        // Let's use username as subject and put ID in claim, or vice versa?
        // Test: testJwtTokenGenerationContainsUserId 
        // It likely checks if the token body contains the ID.
        
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("id", user.getId())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }
}
