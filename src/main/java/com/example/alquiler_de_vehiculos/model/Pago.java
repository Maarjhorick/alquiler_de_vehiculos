package com.example.alquiler_de_vehiculos.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private Integer idPago;

    @ManyToOne
    @JoinColumn(name = "id_alquiler", nullable = false)
    @JsonIgnore
    private Alquiler alquiler;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago", nullable = false)
    private MetodoPago metodoPago;

    @Column(name = "monto_total", precision = 10, scale = 2, nullable = false)
    private BigDecimal montoTotal;

    @Column(name = "fecha_pago")
    private java.time.LocalDate fechaPago;

    @Column(name = "estado_pago", nullable = false, length = 20)
    private String estadoPago;
}
