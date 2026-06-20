package com.example.alquiler_de_vehiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.alquiler_de_vehiculos.model.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer> {
}
