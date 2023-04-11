package com.api.rasfood.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cardapio")
public class Cardapio {

    @GetMapping
    private String teste(){
        return "Ok!!!";
    }
}
