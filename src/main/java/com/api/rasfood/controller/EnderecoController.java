package com.api.rasfood.controller;

import com.api.rasfood.entity.Endereco;
import com.api.rasfood.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ObjectMapper objectMapper; //server para atribuir/setar valores de um lado para o outro

    @GetMapping
    public ResponseEntity<List<Endereco>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(enderecoRepository.findAll());
    }

    @GetMapping("/cep/{cep}")
    public ResponseEntity<List<Endereco>> getAllByCep(@PathVariable("cep") final String cep) {

        if (enderecoRepository.findByCep(cep).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(enderecoRepository.findByCep(cep));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Endereco>> getAllByEmailOrCpf(@PathVariable("id") final Integer id) {

        Optional<Endereco> endereco = enderecoRepository.findById(id);

        if (endereco.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(endereco);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Endereco> update(@PathVariable("id") final Integer id, @RequestBody final Endereco endereco) throws JsonMappingException {
        Optional<Endereco> enderecoFound = this.enderecoRepository.findById(id);

        if (enderecoFound.isPresent()) {
            objectMapper.updateValue(enderecoFound.get(), endereco);
            return ResponseEntity.status(HttpStatus.OK).body(this.enderecoRepository.save(enderecoFound.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

}
