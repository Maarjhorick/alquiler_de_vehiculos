package com.example.alquiler_de_vehiculos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.alquiler_de_vehiculos.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    Optional<Usuario> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByRol_NombreRolIgnoreCase(String nombreRol);

}