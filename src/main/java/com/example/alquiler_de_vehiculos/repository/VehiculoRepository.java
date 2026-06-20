package com.example.alquiler_de_vehiculos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.alquiler_de_vehiculos.model.Vehiculo;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
 Optional<Vehiculo> findFirstByEstadoNombreEstado(String nombreEstado);
 boolean existsByPlacaIgnoreCase(String placa);
 boolean existsByPlacaIgnoreCaseAndIdVehiculoNot(String placa, Integer idVehiculo);
}
