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
@Table(name = "metodospago")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MetodoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMetodoPago;

    @Column(name = "nombre_metodo", nullable = false, unique = true)
    private String nombreMetodo;
}
