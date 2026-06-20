package com.example.alquiler_de_vehiculos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;

import com.example.alquiler_de_vehiculos.model.Alquiler;

@Repository
public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {

  List<Alquiler> findByCliente_IdCliente(Integer idCliente);
  List<Alquiler> findByVehiculo_IdVehiculo(Integer idVehiculo);

  @Query("""
      select (count(a) > 0) from Alquiler a
      where a.vehiculo.idVehiculo = :idVehiculo
        and a.fechaFinReal is null
        and a.fechaInicio <= :fechaFin
        and a.fechaFinEstimada >= :fechaInicio
      """)
  boolean existeCruceActivo(@Param("idVehiculo") Integer idVehiculo,
                            @Param("fechaInicio") LocalDate fechaInicio,
                            @Param("fechaFin") LocalDate fechaFin);
}
