package com.example.alquiler_de_vehiculos.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.alquiler_de_vehiculos.model.Pago;
import com.example.alquiler_de_vehiculos.service.PagoService;

import lombok.RequiredArgsConstructor;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.validation.annotation.Validated;

@RestController
@RequestMapping("/api/pagos")
@RequiredArgsConstructor
@Validated
public class PagoController {

    private final PagoService pagoService;
    @PostMapping
    public Pago registrarPago(@RequestParam Integer idAlquiler,
            @RequestParam Integer idMetodoPago,
            @RequestParam @DecimalMin(value = "0.01", message = "El monto debe ser mayor que cero") BigDecimal monto) {
        return pagoService.registrarPago(idAlquiler, idMetodoPago, monto);
    }

    @GetMapping("/alquiler/{idAlquiler}")
    public List<Pago> obtenerPagosPorAlquiler(@PathVariable("idAlquiler") Integer idAlquiler) {
        return pagoService.obtenerPagosPorAlquiler(idAlquiler);
    }
}
