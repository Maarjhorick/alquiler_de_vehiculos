package com.example.alquiler_de_vehiculos.service;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.alquiler_de_vehiculos.model.Cliente;
import com.example.alquiler_de_vehiculos.repository.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id no encontrado"));
    }

    // crear
    public Cliente create(Cliente cliente) {
        if (cliente.getIdCliente() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id ya existe");

        }
        return repository.save(cliente);
    }

    // actualizar
    public Cliente update(Integer id, Cliente cliente) {
        
        Cliente aux = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id no encontrado"));

        aux.setNombres(cliente.getNombres());
        aux.setApellidos(cliente.getApellidos());
        aux.setTipoDocumento(cliente.getTipoDocumento());
        aux.setNumeroDocumento(cliente.getNumeroDocumento());
        aux.setTelefono(cliente.getTelefono());
        aux.setEmail(cliente.getEmail());
        aux.setLicenciaConducir(cliente.getLicenciaConducir());

        return repository.save(aux);
    }

    // eliminar
    public void delete(Integer id) {
        Cliente aux = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id no encontrado"));

        repository.deleteById(aux.getIdCliente());
    }
}
