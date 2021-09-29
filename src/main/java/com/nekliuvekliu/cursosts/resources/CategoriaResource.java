package com.nekliuvekliu.cursosts.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nekliuvekliu.cursosts.domain.Categoria;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria cat1 = new Categoria (1, "informática");
		Categoria cat2 = new Categoria (2, "Escritório");
		List <Categoria> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		
		
		return lista;
	}
	

}
