package com.example.alquiler_de_vehiculos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.alquiler_de_vehiculos.dto.CrearUsuarioRequest;
import com.example.alquiler_de_vehiculos.model.Rol;
import com.example.alquiler_de_vehiculos.model.Usuario;
import com.example.alquiler_de_vehiculos.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping
    public Usuario crearUsuario(@Valid @RequestBody CrearUsuarioRequest request) {
        return usuarioService.crearUsuario(request);
    }

    @PutMapping("/{idUsuario}/estado")
    public Usuario cambiarEstado(@PathVariable Integer idUsuario, @RequestParam boolean estado) {
        return usuarioService.cambiarEstado(idUsuario, estado);
    }

    @GetMapping("/roles")
    public List<Rol> listarRoles() {
        return usuarioService.listarRoles();
    }
}