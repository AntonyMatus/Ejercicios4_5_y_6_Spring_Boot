package com.example.Ejercicios456.controllers;

import com.example.Ejercicios456.entity.Laptop;
import com.example.Ejercicios456.repository.LaptopRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    //Ejercicio 3
    // Crear un método en LaptopController que reciba un objeto Laptop enviado en formato JSON desde Postman y persistirlo en la base de datos.

    @PostMapping("/api/list-laptop")
    public Laptop create(@RequestBody Laptop laptop){

        //Guardar la Laptop recibido por parametro en la base de datos

        return laptopRepository.save(laptop);
    }
}
