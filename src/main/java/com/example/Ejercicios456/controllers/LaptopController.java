package com.example.Ejercicios456.controllers;

import com.example.Ejercicios456.entity.Laptop;
import com.example.Ejercicios456.repository.LaptopRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LaptopController {

    //atributos
    private LaptopRepository laptopRepository;

    //contructores


    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    // Método que devuelve una lista de objetos Laptop
    @GetMapping("/api/list-laptop")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }
}
