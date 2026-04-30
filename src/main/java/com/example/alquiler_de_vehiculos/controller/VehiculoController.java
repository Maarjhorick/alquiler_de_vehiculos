package com.example.alquiler_de_vehiculos.controller;

import com.example.alquiler_de_vehiculos.model.Vehiculo;
import com.example.alquiler_de_vehiculos.service.VehiculoService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehiculos") // Ruta base para este controlador
public class VehiculoController {

    private final VehiculoService vehiculoService;

    
    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    // --- MÉTODOS CRUD ---

    // 1. OBTENER TODOS LOS VEHÍCULOS (GET)
    // URL: GET http://localhost:8080/api/vehiculos
    @GetMapping
    public List<Vehiculo> obtenerTodos() {
        return vehiculoService.getAllVehiculos();
    }

    // 2. OBTENER UN VEHÍCULO POR ID (GET)
    // URL: GET http://localhost:8080/api/vehiculos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> obtenerPorId(@PathVariable Integer id) {
        return vehiculoService.getVehiculoById(id)
                .map(ResponseEntity::ok) // Si lo encuentra, devuelve 200 OK con el objeto
                .orElseGet(() -> ResponseEntity.notFound().build()); // Si no, devuelve 404 Not Found
    }

    // 3. CREAR UN NUEVO VEHÍCULO (POST)
    // URL: POST http://localhost:8080/api/vehiculos
    // Body (JSON): Ver ejemplo abajo
    @PostMapping
    public ResponseEntity<Vehiculo> crearVehiculo(@RequestBody Vehiculo vehiculo) {
        Vehiculo nuevoVehiculo = vehiculoService.saveVehiculo(vehiculo);
        return new ResponseEntity<>(nuevoVehiculo, HttpStatus.CREATED); // Devuelve 201 Created
    }

    // 4. ACTUALIZAR UN VEHÍCULO EXISTENTE (PUT)
    // URL: PUT http://localhost:8080/api/vehiculos/{id}
    // Body (JSON): Ver ejemplo abajo
    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable Integer id, @RequestBody Vehiculo vehiculoDetalles) {
        try {
            Vehiculo vehiculoActualizado = vehiculoService.updateVehiculo(id, vehiculoDetalles);
            return ResponseEntity.ok(vehiculoActualizado); // Devuelve 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Devuelve 404 si el ID no existe
        }
    }

    // 5. ELIMINAR UN VEHÍCULO (DELETE)
    // URL: DELETE http://localhost:8080/api/vehiculos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVehiculo(@PathVariable Integer id) {
        // Una buena práctica es verificar si existe antes de intentar borrar
        if (vehiculoService.getVehiculoById(id).isPresent()) {
            vehiculoService.deleteVehiculo(id);
            return ResponseEntity.noContent().build(); // Devuelve 204 No Content (éxito)
        } else {
            return ResponseEntity.notFound().build(); // Devuelve 404 si el ID no existe
        }
    }
}