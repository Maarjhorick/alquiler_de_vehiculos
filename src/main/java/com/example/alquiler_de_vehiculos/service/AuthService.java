package com.example.alquiler_de_vehiculos.service;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.alquiler_de_vehiculos.dto.AuthResponse;
import com.example.alquiler_de_vehiculos.dto.LoginRequest;
import com.example.alquiler_de_vehiculos.dto.RegistroRequest;
import com.example.alquiler_de_vehiculos.model.Cliente;
import com.example.alquiler_de_vehiculos.model.Rol;
import com.example.alquiler_de_vehiculos.model.Usuario;
import com.example.alquiler_de_vehiculos.repository.ClienteRepository;
import com.example.alquiler_de_vehiculos.repository.RolRepository;
import com.example.alquiler_de_vehiculos.repository.UsuarioRepository;
import com.example.alquiler_de_vehiculos.security.CustomUserDetailService;
import com.example.alquiler_de_vehiculos.security.JwtService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailService userDetailsService;
    private final JwtService jwtService;

    @Transactional
    public AuthResponse registrar(RegistroRequest request) {
        if (usuarioRepository.existsByUsername(request.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El username ya existe");
        }

        String nombreRol = usuarioRepository.existsByRol_NombreRolIgnoreCase("ADMIN") ? "CLIENTE" : "ADMIN";
        Rol rol = rolRepository.findByNombreRolIgnoreCase(nombreRol)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Rol no configurado: " + nombreRol));
        Cliente cliente = request.idCliente() == null ? null : clienteRepository.findById(request.idCliente())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setUsername(request.username());
        usuario.setPassword(passwordEncoder.encode(request.password()));
        usuario.setEmailRecuperacion(request.emailRecuperacion());
        usuario.setRol(rol);
        usuario.setCliente(cliente);
        usuario.setEstado(true);
        usuario.setFechaCreacion(LocalDateTime.now());
        usuarioRepository.save(usuario);

        UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getUsername());
        return respuesta(userDetails, rol);
    }

    @Transactional
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        Usuario usuario = usuarioRepository.findByUsername(request.username())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales invalidas"));
        usuario.setUltimoAcceso(LocalDateTime.now());
        usuarioRepository.save(usuario);
        return respuesta(userDetailsService.loadUserByUsername(request.username()), usuario.getRol());
    }

    private AuthResponse respuesta(UserDetails userDetails, Rol rol) {
        return new AuthResponse(jwtService.generarToken(userDetails), "Bearer",
                userDetails.getUsername(), rol.getNombreRol().toUpperCase());
    }
}
