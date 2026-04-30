package com.example.alquiler_de_vehiculos.repository;

import com.example.alquiler_de_vehiculos.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// JpaRepository<Entidad, Tipo_de_Dato_de_la_Clave_Primaria>
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    
    // Aquí podrías agregar métodos de búsqueda personalizados, por ejemplo:
    // List<Vehiculo> findByPlaca(String placa);
}