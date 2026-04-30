package com.example.alquiler_de_vehiculos.model; 

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "vehiculos") 
@Getter 
@Setter
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Integer id; 

    @Column(nullable = false, length = 20)
    private String placa;

    @Column(length = 50)
    private String color;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "numero_motor", length = 100)
    private String numeroMotor;

    @Column(name = "numero_vin", length = 100)
    private String numeroVin;

    @Column(name = "precio_dia", precision = 10, scale = 2)
    private BigDecimal precioDia;

    @Column(name = "precio_hora", precision = 10, scale = 2)
    private BigDecimal precioHora;

   
    @Column(name = "id_modelo", nullable = false)
    private Integer idModelo;

    @Column(name = "id_tipo", nullable = false)
    private Integer idTipo;

    @Column(name = "id_combustible", nullable = false)
    private Integer idCombustible;

    @Column(name = "id_estado", nullable = false)
    private Integer idEstado;

    public Vehiculo() {
    }

    public Vehiculo(String placa, String color, Integer anio, BigDecimal precioDia, 
                    Integer idModelo, Integer idTipo, Integer idCombustible, Integer idEstado) {
        this.placa = placa;
        this.color = color;
        this.anio = anio;
        this.precioDia = precioDia;
        this.idModelo = idModelo;
        this.idTipo = idTipo;
        this.idCombustible = idCombustible;
        this.idEstado = idEstado;
    }
}