package com.example.alquiler_de_vehiculos.service;

import com.example.alquiler_de_vehiculos.model.Vehiculo;
import com.example.alquiler_de_vehiculos.model.Modelo;
import com.example.alquiler_de_vehiculos.model.TipoVehiculo;
import com.example.alquiler_de_vehiculos.model.Combustible;
import com.example.alquiler_de_vehiculos.model.EstadoVehiculo;
import com.example.alquiler_de_vehiculos.repository.VehiculoRepository;
import com.example.alquiler_de_vehiculos.repository.ModeloRepository;
import com.example.alquiler_de_vehiculos.repository.TipoVehiculoRepository;
import com.example.alquiler_de_vehiculos.repository.CombustibleRepository;
import com.example.alquiler_de_vehiculos.repository.EstadoVehiculoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final ModeloRepository modeloRepository;
    private final TipoVehiculoRepository tipoRepository;
    private final CombustibleRepository combustibleRepository;
    private final EstadoVehiculoRepository estadoRepository;

    @Transactional
    public Vehiculo saveVehiculo(Vehiculo vehiculo) {
        if (vehiculoRepository.existsByPlacaIgnoreCase(vehiculo.getPlaca())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La placa ya existe");
        }
        // Buscar entidades relacionadas existentes
        Modelo modelo = modeloRepository.findById(vehiculo.getModelo().getIdModelo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modelo no encontrado"));
        TipoVehiculo tipo = tipoRepository.findById(vehiculo.getTipo().getIdTipo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de vehiculo no encontrado"));
        Combustible combustible = combustibleRepository.findById(vehiculo.getCombustible().getIdCombustible())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Combustible no encontrado"));
        EstadoVehiculo estado = estadoRepository.findById(vehiculo.getEstado().getIdEstado())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado no encontrado"));

        vehiculo.setModelo(modelo);
        vehiculo.setTipo(tipo);
        vehiculo.setCombustible(combustible);
        vehiculo.setEstado(estado);

        return vehiculoRepository.save(vehiculo);
    }

    public List<Vehiculo> getAllVehiculos() {
        return vehiculoRepository.findAll();
    }

    public Optional<Vehiculo> getVehiculoById(Integer id) {
        return vehiculoRepository.findById(id);
    }

    @Transactional
    public void deleteVehiculo(Integer id) {
        vehiculoRepository.deleteById(id);
    }

    @Transactional
    public Vehiculo updateVehiculo(Integer idVehiculo, Vehiculo vehiculoDetalles) {

        Vehiculo vehiculoExistente = vehiculoRepository.findById(idVehiculo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no encontrado"));
        if (vehiculoRepository.existsByPlacaIgnoreCaseAndIdVehiculoNot(vehiculoDetalles.getPlaca(), idVehiculo)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "La placa ya existe");
        }

        vehiculoExistente.setPlaca(vehiculoDetalles.getPlaca());
        vehiculoExistente.setColor(vehiculoDetalles.getColor());
        vehiculoExistente.setAnio(vehiculoDetalles.getAnio());
        vehiculoExistente.setNumeroMotor(vehiculoDetalles.getNumeroMotor());
        vehiculoExistente.setNumeroVin(vehiculoDetalles.getNumeroVin());
        vehiculoExistente.setPrecioDia(vehiculoDetalles.getPrecioDia());
        vehiculoExistente.setPrecioHora(vehiculoDetalles.getPrecioHora());

        // Buscar entidades relacionadas en BD antes de asignar
        Modelo modelo = modeloRepository.findById(vehiculoDetalles.getModelo().getIdModelo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modelo no encontrado"));
        TipoVehiculo tipo = tipoRepository.findById(vehiculoDetalles.getTipo().getIdTipo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo de vehiculo no encontrado"));
        Combustible combustible = combustibleRepository.findById(vehiculoDetalles.getCombustible().getIdCombustible())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Combustible no encontrado"));
        EstadoVehiculo estado = estadoRepository.findById(vehiculoDetalles.getEstado().getIdEstado())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estado no encontrado"));

        vehiculoExistente.setModelo(modelo);
        vehiculoExistente.setTipo(tipo);
        vehiculoExistente.setCombustible(combustible);
        vehiculoExistente.setEstado(estado);

        return vehiculoRepository.save(vehiculoExistente);
    }
}
