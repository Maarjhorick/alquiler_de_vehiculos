package com.example.alquiler_de_vehiculos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.alquiler_de_vehiculos.dto.AuthResponse;
import com.example.alquiler_de_vehiculos.dto.LoginRequest;
import com.example.alquiler_de_vehiculos.dto.RegistroRequest;
import com.example.alquiler_de_vehiculos.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.Map;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registro")
    public ResponseEntity<AuthResponse> registrar(@Valid @RequestBody RegistroRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.registrar(request));
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public Map<String, Object> usuarioActual(Authentication authentication) {
        return Map.of(
                "username", authentication.getName(),
                "roles", authentication.getAuthorities());
    }
}
