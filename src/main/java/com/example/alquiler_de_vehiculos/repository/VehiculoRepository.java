package com.example.alquiler_de_vehiculos.repository;

import com.example.alquiler_de_vehiculos.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    
}