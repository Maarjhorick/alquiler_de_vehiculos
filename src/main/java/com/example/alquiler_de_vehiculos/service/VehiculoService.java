package com.example.alquiler_de_vehiculos.service;

import com.example.alquiler_de_vehiculos.model.Vehiculo;
import com.example.alquiler_de_vehiculos.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    // CREATE / UPDATE
    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    // READ (All)
    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    // READ (By ID)
    public Optional<Vehiculo> getVehiculoById(Integer id) {
        return vehiculoRepository.findById(id);
    }

    // DELETE
    public void deleteVehiculo(Integer id) {
        vehiculoRepository.deleteById(id);
    }
    
    // Para el método PUT (Actualizar), necesitamos una lógica adicional
    public Vehiculo updateVehiculo(Integer id, Vehiculo vehiculoDetalles) {
        // 1. Buscar el vehículo existente
        Vehiculo vehiculoExistente = vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con id: " + id));

        // 2. Actualizar los campos
        vehiculoExistente.setPlaca(vehiculoDetalles.getPlaca());
        vehiculoExistente.setColor(vehiculoDetalles.getColor());
        vehiculoExistente.setAnio(vehiculoDetalles.getAnio());
        vehiculoExistente.setNumeroMotor(vehiculoDetalles.getNumeroMotor());
        vehiculoExistente.setNumeroVin(vehiculoDetalles.getNumeroVin());
        vehiculoExistente.setPrecioDia(vehiculoDetalles.getPrecioDia());
        vehiculoExistente.setPrecioHora(vehiculoDetalles.getPrecioHora());
        vehiculoExistente.setIdModelo(vehiculoDetalles.getIdModelo());
        vehiculoExistente.setIdTipo(vehiculoDetalles.getIdTipo());
        vehiculoExistente.setIdCombustible(vehiculoDetalles.getIdCombustible());
        vehiculoExistente.setIdEstado(vehiculoDetalles.getIdEstado());

        // 3. Guardar el vehículo actualizado
        return vehiculoRepository.save(vehiculoExistente);
    }
}