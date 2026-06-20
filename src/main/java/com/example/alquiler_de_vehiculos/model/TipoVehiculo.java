package com.example.alquiler_de_vehiculos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tiposvehiculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipo;

    @Column(name = "nombre_tipo", nullable = false, unique = true)
    private String nombreTipo;
}
