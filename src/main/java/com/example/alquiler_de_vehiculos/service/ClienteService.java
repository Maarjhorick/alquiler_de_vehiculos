package com.example.alquiler_de_vehiculos.service;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.alquiler_de_vehiculos.model.Cliente;
import com.example.alquiler_de_vehiculos.repository.ClienteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerCliente(Integer idCliente) {
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
    }

   @Transactional
    public Cliente crearCliente(Cliente cliente) {
        if (cliente.getIdCliente() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente ya existe");

        }
        validarDocumentoUnico(cliente.getNumeroDocumento(), null);
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente actualizarCliente(Integer idCliente, Cliente cliente) {
        
        Cliente aux = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id no encontrado"));
        validarDocumentoUnico(cliente.getNumeroDocumento(), idCliente);

        aux.setNombres(cliente.getNombres());
        aux.setApellidos(cliente.getApellidos());
        aux.setTipoDocumento(cliente.getTipoDocumento());
        aux.setNumeroDocumento(cliente.getNumeroDocumento());
        aux.setTelefono(cliente.getTelefono());
        aux.setEmail(cliente.getEmail());
        aux.setLicenciaConducir(cliente.getLicenciaConducir());

        return clienteRepository.save(aux);
    }

    @Transactional
    public void eliminarCliente(Integer idCliente) {
        Cliente aux = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id no encontrado"));

        clienteRepository.deleteById(aux.getIdCliente());
    }

    private void validarDocumentoUnico(String numeroDocumento, Integer idCliente) {
        clienteRepository.findByNumeroDocumento(numeroDocumento)
                .filter(cliente -> !cliente.getIdCliente().equals(idCliente))
                .ifPresent(cliente -> {
                    throw new ResponseStatusException(HttpStatus.CONFLICT, "El numero de documento ya existe");
                });
    }
}
