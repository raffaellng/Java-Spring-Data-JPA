package com.api.rasfood.controller;

import com.api.rasfood.entity.Cardapio;
import com.api.rasfood.repository.CardapioRepository;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cardapio")
public class CardapioController {

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private ObjectMapper objectMapper; //server para atribuir/setar valores de um lado para o outro

    @GetMapping
    public ResponseEntity<List<Cardapio>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(cardapioRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cardapio> getByEmailOrCpf(@PathVariable("id") final Integer id) {

        Optional<Cardapio> cardapio = cardapioRepository.findById(id);
        if (cardapio.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(cardapio.get());
    }

    @PostMapping
    public ResponseEntity<Cardapio> create(@RequestBody final Cardapio cardapio) throws JsonMappingException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cardapioRepository.save(cardapio));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cardapio> update(@PathVariable("id") final Integer id, @RequestBody final Cardapio cardapio) throws JsonMappingException {
        Optional<Cardapio> cardapioFound = this.cardapioRepository.findById(id);

        if (cardapioFound.isPresent()) {
            objectMapper.updateValue(cardapioFound.get(), cardapio);
            return ResponseEntity.status(HttpStatus.OK).body(this.cardapioRepository.save(cardapioFound.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") final Integer id) {

        if (cardapioRepository.findById(id).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Elemento não encontrado");

        cardapioRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("SUCCESS");
    }
}
