package com.example.smart_library.controller;

import com.example.smart_library.dto.auth.LoginRequest;
import com.example.smart_library.dto.auth.LoginResponse;
import com.example.smart_library.dto.auth.RegisterRequest;
import com.example.smart_library.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return new ResponseEntity<>("Kullanıcı başarıyla kaydedildi.", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}