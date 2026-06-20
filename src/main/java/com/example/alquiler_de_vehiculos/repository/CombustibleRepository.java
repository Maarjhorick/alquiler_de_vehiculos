package com.example.alquiler_de_vehiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.alquiler_de_vehiculos.model.Combustible;

@Repository
public interface CombustibleRepository extends JpaRepository<Combustible, Integer> {
}
