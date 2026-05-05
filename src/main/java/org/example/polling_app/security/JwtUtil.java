package org.example.polling_app.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.polling_app.model.Teacher;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET =
            "my-super-secret-key-for-polling-app-change-this-later-123456";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 2;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Teacher teacher) {
        return Jwts.builder()
                .subject(String.valueOf(teacher.getId()))
                .claim("username", teacher.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey())
                .compact();
    }

    public Long extractTeacherId(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(cleanToken(token))
                .getPayload();

        return Long.parseLong(claims.getSubject());
    }

    private String cleanToken(String token) {
        return token.replace("Bearer ", "");
    }
}