package com.example.Ejercicios456;

import com.example.Ejercicios456.entity.Laptop;
import com.example.Ejercicios456.repository.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Ejercicios456Application {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Ejercicios456Application.class, args);
		LaptopRepository repository = context.getBean(LaptopRepository.class);

		//crear Laptop

		Laptop laptop1 = new Laptop(null,"Lenovo","IdeaPad 3 15",15399.00,true);
		Laptop laptop2 = new Laptop(null,"Lenovo","IdeaPad 3 14",16368.00,true);
		Laptop laptop3 = new Laptop(null,"Lenovo","IdeaPad Gaming 3i 15",18499.00,true);

		// almacenar las Laptops

		repository.save(laptop1);
		repository.save(laptop2);
		repository.save(laptop3);

		System.out.println("Num de elempletos: "+ repository.findAll().size());

	}

}
