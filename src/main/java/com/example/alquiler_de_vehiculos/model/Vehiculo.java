package com.example.alquiler_de_vehiculos.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "vehiculos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehiculo")
    private Integer idVehiculo;

    @NotBlank
    @Column(nullable = false, unique = true, length = 20)
    private String placa;

    @Column(length = 50)
    @NotBlank
    private String color;

    @Column(name = "anio")
    private Integer anio;

    @NotBlank
    @Column(name = "numero_motor", unique = true, nullable = false, length = 20)
    private String numeroMotor;

    @NotBlank
    @Column(name = "numero_vin", unique = true, nullable = false, length = 17)
    private String numeroVin;

    @NotNull
    @PositiveOrZero
    @Column(name = "precio_dia", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioDia;

    @NotNull
    @PositiveOrZero
    @Column(name = "precio_hora", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioHora;

    // Relaciones con otras tablas
    @ManyToOne
    @JoinColumn(name = "id_modelo", nullable = false)
    @NotNull
    @Valid
    private Modelo modelo;

    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable = false)
    @NotNull
    @Valid
    private TipoVehiculo tipo;

    @ManyToOne
    @JoinColumn(name = "id_combustible", nullable = false)
    @NotNull
    @Valid
    private Combustible combustible;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    @NotNull
    @Valid
    private EstadoVehiculo estado;

}
