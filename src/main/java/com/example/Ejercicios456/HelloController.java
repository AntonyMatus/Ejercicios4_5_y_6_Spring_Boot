package com.example.Ejercicios456;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/Saludo")
    public String Hellow(){

        return "Hola que tal te encuentras HOY?";
    }
}
