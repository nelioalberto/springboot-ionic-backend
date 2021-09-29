package com.nekliuvekliu.cursosts;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nekliuvekliu.cursosts.domain.Categoria;
import com.nekliuvekliu.cursosts.repositories.CategoriaRepository;

@SpringBootApplication
public class CursostsApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CursostsApplication.class, args);
	}

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria (null, "informática");
		Categoria cat2 = new Categoria (null, "Escritório");
	
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
		
	}

}
