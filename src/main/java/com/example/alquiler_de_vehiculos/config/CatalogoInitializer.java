package com.example.alquiler_de_vehiculos.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.alquiler_de_vehiculos.model.EstadoAlquiler;
import com.example.alquiler_de_vehiculos.model.EstadoVehiculo;
import com.example.alquiler_de_vehiculos.repository.EstadoAlquilerRepository;
import com.example.alquiler_de_vehiculos.repository.EstadoVehiculoRepository;
import com.example.alquiler_de_vehiculos.model.Combustible;
import com.example.alquiler_de_vehiculos.model.Marca;
import com.example.alquiler_de_vehiculos.model.MetodoPago;
import com.example.alquiler_de_vehiculos.model.Modelo;
import com.example.alquiler_de_vehiculos.model.Rol;
import com.example.alquiler_de_vehiculos.model.TipoVehiculo;
import com.example.alquiler_de_vehiculos.repository.CombustibleRepository;
import com.example.alquiler_de_vehiculos.repository.MarcaRepository;
import com.example.alquiler_de_vehiculos.repository.MetodoPagoRepository;
import com.example.alquiler_de_vehiculos.repository.ModeloRepository;
import com.example.alquiler_de_vehiculos.repository.RolRepository;
import com.example.alquiler_de_vehiculos.repository.TipoVehiculoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CatalogoInitializer implements CommandLineRunner {

    private final EstadoVehiculoRepository estadoVehiculoRepository;
    private final EstadoAlquilerRepository estadoAlquilerRepository;
    private final RolRepository rolRepository;
    private final MarcaRepository marcaRepository;
    private final ModeloRepository modeloRepository;
    private final TipoVehiculoRepository tipoVehiculoRepository;
    private final CombustibleRepository combustibleRepository;
    private final MetodoPagoRepository metodoPagoRepository;

    @Override
    public void run(String... args) {
        crearEstadoVehiculo("DISPONIBLE");
        crearEstadoVehiculo("NO DISPONIBLE");
        crearEstadoAlquiler("ACTIVO");
        crearEstadoAlquiler("FINALIZADO");
        crearRol("ADMIN", "Administrador principal del sistema");
        crearRol("CLIENTE", "Usuario de la plataforma de alquiler");
        crearCatalogosPrueba();
    }

    private void crearEstadoVehiculo(String nombre) {
        estadoVehiculoRepository.findByNombreEstadoIgnoreCase(nombre)
                .orElseGet(() -> estadoVehiculoRepository.save(new EstadoVehiculo(null, nombre)));
    }

    private void crearEstadoAlquiler(String nombre) {
        estadoAlquilerRepository.findByEstadoAlquilerIgnoreCase(nombre)
                .orElseGet(() -> estadoAlquilerRepository.save(new EstadoAlquiler(null, nombre)));
    }

    private void crearRol(String nombre, String descripcion) {
        rolRepository.findByNombreRolIgnoreCase(nombre)
                .orElseGet(() -> rolRepository.save(new Rol(null, nombre, descripcion)));
    }

    private void crearCatalogosPrueba() {
        Marca marca = marcaRepository.findAll().stream().findFirst()
                .orElseGet(() -> marcaRepository.save(new Marca(null, "Toyota")));
        if (modeloRepository.count() == 0) {
            modeloRepository.save(new Modelo(null, "Corolla", marca));
        }
        if (tipoVehiculoRepository.count() == 0) {
            tipoVehiculoRepository.save(new TipoVehiculo(null, "SEDAN"));
        }
        if (combustibleRepository.count() == 0) {
            combustibleRepository.save(new Combustible(null, "GASOLINA"));
        }
        if (metodoPagoRepository.count() == 0) {
            metodoPagoRepository.save(new MetodoPago(null, "TARJETA"));
        }
    }
}
