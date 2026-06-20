package com.example.alquiler_de_vehiculos.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.alquiler_de_vehiculos.model.Alquiler;
import com.example.alquiler_de_vehiculos.service.AlquilerService;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/alquileres")
@RequiredArgsConstructor
@Validated
public class AlquilerController {

    private final AlquilerService alquilerService;

    @PostMapping
    public Alquiler crearAlquiler(@RequestParam @NotNull Integer idCliente,
                                  @RequestParam @NotNull Integer idVehiculo,
                                  @RequestParam @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
                                  @RequestParam @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return alquilerService.crearAlquiler(idCliente, idVehiculo, inicio, fin);
    }

    @GetMapping("/cliente/{idCliente}")
    public List<Alquiler> obtenerAlquileresPorCliente(@PathVariable("idCliente") Integer idCliente) {
        return alquilerService.obtenerAlquileresPorCliente(idCliente);
    }

    @GetMapping("/vehiculo/{idVehiculo}")
    public List<Alquiler> obtenerAlquileresPorVehiculo(@PathVariable("idVehiculo") Integer idVehiculo) {
        return alquilerService.obtenerAlquileresPorVehiculo(idVehiculo);
    }

    @PutMapping("/{idAlquiler}/finalizar")
    public Alquiler finalizarAlquiler(@PathVariable Integer idAlquiler,
                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFinReal) {
        return alquilerService.finalizarAlquiler(idAlquiler, fechaFinReal);
    }
}
