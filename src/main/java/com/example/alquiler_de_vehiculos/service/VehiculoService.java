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

    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    public Optional<Vehiculo> getVehiculoById(Integer id) {
        return vehiculoRepository.findById(id);
    }

    public void deleteVehiculo(Integer id) {
        vehiculoRepository.deleteById(id);
    }

    public Vehiculo updateVehiculo(Integer id, Vehiculo vehiculoDetalles) {
  
        Vehiculo vehiculoExistente = vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con id: " + id));

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

        return vehiculoRepository.save(vehiculoExistente);
    }
}