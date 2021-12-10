package com.example.Ejercicios456.entity;

import javax.persistence.*;

@Entity
@Table(name = "laptops")
public class Laptop {

    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Marca;
    private String Modelo;
    private Double price;
    private boolean offers;

    //contructores

   public Laptop(){}

    public Laptop(Long id, String marca, String modelo, Double price, boolean offers) {
        this.id = id;
        Marca = marca;
        Modelo = modelo;
        this.price = price;
        this.offers = offers;
    }

    //getter y setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isOffers() {
        return offers;
    }

    public void setOffers(boolean offers) {
        this.offers = offers;
    }
}
