package com.api.rasfood.controller;

import com.api.rasfood.entity.Cliente;
import com.api.rasfood.entity.ClienteId;
import com.api.rasfood.repository.ClienteRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(clienteRepository.findAll());
    }

    @GetMapping("/{email}/{cpf}")
    public ResponseEntity<Cliente> getByEmailOrCpf(@PathVariable("email") final String email, @PathVariable("cpf") final String cpf) {
        ClienteId clienteId = new ClienteId(email, cpf);
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if (!cliente.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(cliente.get());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable("id") final String id, @RequestBody final Cliente cliente) throws JsonMappingException {
        Optional<Cliente> clienteFound = this.clienteRepository.findByEmailOrCpf(id);

        if (clienteFound.isPresent()) {
            objectMapper.updateValue(clienteFound.get(), cliente);
            return ResponseEntity.status(HttpStatus.OK).body(this.clienteRepository.save(clienteFound.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}