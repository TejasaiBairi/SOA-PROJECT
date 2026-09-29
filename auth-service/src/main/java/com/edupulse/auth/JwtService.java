package com.edupulse.auth;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import org.springframework.stereotype.Service;
@Service
public class JwtService {
 private static final String SECRET="EduPulseSuperSecretKeyForJWTAuthentication2026ChangeMe123456";
 private SecretKey key(){return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));}
 public String generate(User user){
   return Jwts.builder().subject(user.getUsername()).claim("role",user.getRole())
     .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis()+86400000))
     .signWith(key()).compact();
 }
}
