package com.example.alquiler_de_vehiculos.model; // Asegúrate de que este package sea el correcto

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "vehiculos") // Nombre de la tabla en tu base de datos
@Getter // Si no usas Lombok, tendrás que generar los Getters y Setters manualmente
@Setter
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Integer id; // Usamos Integer en Java para INT(11)

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

    // Campos para relaciones (Foreign Keys) - Por ahora los manejaremos como IDs
    // Más adelante, para un CRUD completo, deberías crear las Entidades (Modelo, Tipo, etc.)
    // y usar @ManyToOne
    @Column(name = "id_modelo", nullable = false)
    private Integer idModelo;

    @Column(name = "id_tipo", nullable = false)
    private Integer idTipo;

    @Column(name = "id_combustible", nullable = false)
    private Integer idCombustible;

    @Column(name = "id_estado", nullable = false)
    private Integer idEstado;

    // Constructor vacío (obligatorio para JPA)
    public Vehiculo() {
    }

    // Constructor con campos (opcional pero útil)
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