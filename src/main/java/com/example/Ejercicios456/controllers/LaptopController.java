package com.example.Ejercicios456.controllers;

import com.example.Ejercicios456.entity.Laptop;
import com.example.Ejercicios456.repository.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {

    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    //atributos
    private LaptopRepository laptopRepository;

    //contructores


    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    // Método que devuelve una lista de objetos Laptop
    @GetMapping("/api/list-laptop")
    public List<Laptop> listLaptop(){
        return laptopRepository.findAll();
    }

    //Ejercicio 3
    // Crear un método en LaptopController que reciba un objeto Laptop enviado en formato JSON desde Postman y persistirlo en la base de datos.

    @PostMapping("/api/list-laptop")
    public Laptop createPostman(@RequestBody Laptop laptop){

        //Guardar la Laptop recibido por parametro en la base de datos

        return laptopRepository.save(laptop);
    }

    //Ejercicio sesion 7:
    /**
     * Implementar los métodos CRUD en el API REST de Laptop creada en ejercicios anteriores.
     * findAll()
     *
     * findOneById()
     *
     * create()
     *
     * update()
     *
     * delete()
     *
     * deleteAll()
     */

    @GetMapping("/api/laptops")
    @ApiOperation("Buscar Todas las Laptops en una Lista")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }


    @GetMapping("/api/laptops/{id}")
    @ApiOperation("Buscar una Laptop por clave primaria id Long")
    public ResponseEntity<Laptop> findOneById(@ApiParam("Clave primaria tipo Long") @PathVariable Long id){
        Optional<Laptop> laptopOpt = laptopRepository.findById(id);

        if (laptopOpt.isPresent())
            return ResponseEntity.ok(laptopOpt.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/laptops")
    @ApiOperation("Crear un nuevo Registro de una Laptop")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){

        if (laptop.getId() != null){
            log.warn("trying to create a laptop with id");
            System.out.println("trying to create a laptop with id");
            return ResponseEntity.badRequest().build();
        }

        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);

    }

    @PutMapping("/api/laptops")
    @ApiOperation("Actualizar una Laptop segun la clave primaria")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if (laptop.getId() == null){
            log.warn("Trying to update a nonn existent laptop");
            return ResponseEntity.badRequest().build();

        }
        if (!laptopRepository.existsById(laptop.getId())){
            log.warn("Trying to update a non existent laptop");
            return ResponseEntity.notFound().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }


    @DeleteMapping("/api/laptops/{id}")
    @ApiOperation("Eliminar un registro mediante la Clave id")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){

        if (!laptopRepository.existsById(id)){
            log.warn("Trying to delete a non existent laptop");
            return ResponseEntity.notFound().build();
        }

        laptopRepository.deleteById(id);

        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/api/laptops")
    @ApiOperation("Eliminar todos los Registros de la BD")
    public ResponseEntity<Laptop> deleteAll(){
        log.info("REST Request fot delete all laptops");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
