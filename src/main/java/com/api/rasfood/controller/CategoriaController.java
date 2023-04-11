package com.api.rasfood.controller;

import com.api.rasfood.entity.Categoria;
import com.api.rasfood.repository.CategoriaRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ObjectMapper objectMapper; //server para atribuir/setar valores de um lado para o outro

    @GetMapping
    public ResponseEntity<List<Categoria>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getByEmailOrCpf(@PathVariable("id") final Integer id) {

        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(categoria.get());
    }

    @PostMapping
    public ResponseEntity<Categoria> create(@RequestBody final Categoria categoria) throws JsonMappingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoriaRepository.save(categoria));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable("id") final Integer id, @RequestBody final Categoria categoria) throws JsonMappingException {
        Optional<Categoria> categoriaFound = this.categoriaRepository.findById(id);

        if (categoriaFound.isPresent()) {
            objectMapper.updateValue(categoriaFound.get(), categoria);
            return ResponseEntity.status(HttpStatus.OK).body(this.categoriaRepository.save(categoriaFound.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") final Integer id) {

        if (categoriaRepository.findById(id).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Elemento não encontrado");

        categoriaRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("SUCCESS");
    }
}
