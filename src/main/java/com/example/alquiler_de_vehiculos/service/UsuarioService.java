package com.example.alquiler_de_vehiculos.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.alquiler_de_vehiculos.dto.CrearUsuarioRequest;
import com.example.alquiler_de_vehiculos.model.Rol;
import com.example.alquiler_de_vehiculos.model.Usuario;
import com.example.alquiler_de_vehiculos.repository.RolRepository;
import com.example.alquiler_de_vehiculos.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario crearUsuario(CrearUsuarioRequest request) {
        if (usuarioRepository.existsByUsername(request.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El nombre de usuario ya existe");
        }

        Rol rol = rolRepository.findByNombreRolIgnoreCase(request.rolNombre())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "El rol '" + request.rolNombre() + "' no existe"));

        if (rol.getNombreRol().equalsIgnoreCase("CLIENTE")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Los usuarios CLIENTE se crean mediante el registro público, no desde este panel");
        }

        Usuario usuario = new Usuario();
        usuario.setUsername(request.username());
        usuario.setPassword(passwordEncoder.encode(request.password()));
        usuario.setEmailRecuperacion(request.emailRecuperacion());
        usuario.setRol(rol);
        usuario.setEstado(true);
        usuario.setFechaCreacion(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }

    public Usuario cambiarEstado(Integer idUsuario, boolean estado) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));

        usuario.setEstado(estado);
        return usuarioRepository.save(usuario);
    }

    public List<Rol> listarRoles() {
        return rolRepository.findAll();
    }
}