package com.example.Carte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class CarteApplication {

	public static void main(String[] args) {
		System.out.println("==== Aplicația Carte a pornit cu succes ====");
		SpringApplication.run(CarteApplication.class, args);
	}
}

// http://localhost:8080/lista-carti
