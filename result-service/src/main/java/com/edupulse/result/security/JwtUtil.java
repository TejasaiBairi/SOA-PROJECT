package com.edupulse.result.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Component;
@Component
public class JwtUtil {
    private static final String SECRET = "EduPulseSuperSecretKeyForJWTAuthentication2026ChangeMe123456";
    private SecretKey key() { return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8)); }
    public String username(String token) { return claims(token).getSubject(); }
    public String role(String token) { return claims(token).get("role", String.class); }
    public boolean valid(String token) { try { claims(token); return true; } catch(Exception e) { return false; } }
    private Claims claims(String token) { return Jwts.parser().verifyWith(key()).build().parseSignedClaims(token).getPayload(); }
}
