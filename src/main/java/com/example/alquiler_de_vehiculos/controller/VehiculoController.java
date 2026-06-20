package com.example.alquiler_de_vehiculos.controller;

import com.example.alquiler_de_vehiculos.model.Vehiculo;
import com.example.alquiler_de_vehiculos.service.VehiculoService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/vehiculos") 
public class VehiculoController {

    private final VehiculoService vehiculoService;

    
    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }


    @GetMapping
    public List<Vehiculo> obtenerTodos() {
        return vehiculoService.getAllVehiculos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtenerPorId(@PathVariable Integer id) {
        return vehiculoService.getVehiculoById(id)
                .map(ResponseEntity::ok) 
                .orElseGet(() -> ResponseEntity.notFound().build()); 
    }

    @PostMapping
    public ResponseEntity<Vehiculo> crearVehiculo(@Valid @RequestBody Vehiculo vehiculo) {
        Vehiculo nuevoVehiculo = vehiculoService.saveVehiculo(vehiculo);
        return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable Integer id, @Valid @RequestBody Vehiculo vehiculoDetalles) {
        return ResponseEntity.ok(vehiculoService.updateVehiculo(id, vehiculoDetalles));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVehiculo(@PathVariable Integer id) {
        if (vehiculoService.getVehiculoById(id).isPresent()) {
            vehiculoService.deleteVehiculo(id);
            return ResponseEntity.noContent().build(); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
}
