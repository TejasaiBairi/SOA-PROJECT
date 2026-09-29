package com.edupulse.auth;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/auth")
public class AuthController {
 private final UserRepository repo; private final PasswordEncoder encoder; private final JwtService jwt;
 public AuthController(UserRepository repo,PasswordEncoder encoder,JwtService jwt){this.repo=repo;this.encoder=encoder;this.jwt=jwt;}
 @PostMapping("/register")
 public ResponseEntity<?> register(@RequestBody RegisterRequest r){
   if(repo.findByUsername(r.username()).isPresent()) return ResponseEntity.badRequest().body(Map.of("message","Username already exists"));
   String role=(r.role()==null||r.role().isBlank())?"FACULTY":r.role().toUpperCase();
   if(!role.equals("FACULTY")&&!role.equals("ADMIN")) return ResponseEntity.badRequest().body(Map.of("message","Role must be FACULTY or ADMIN"));
   User u=new User(r.username(),encoder.encode(r.password()),role); repo.save(u);
   return ResponseEntity.ok(Map.of("message","User registered","role",role));
 }
 @PostMapping("/login")
 public ResponseEntity<?> login(@RequestBody LoginRequest r){
   var u=repo.findByUsername(r.username()).orElse(null);
   if(u==null||!encoder.matches(r.password(),u.getPassword())) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message","Invalid credentials"));
   return ResponseEntity.ok(Map.of("token",jwt.generate(u),"username",u.getUsername(),"role",u.getRole()));
 }
 record RegisterRequest(String username,String password,String role){}
 record LoginRequest(String username,String password){}
}
