package com.nekliuvekliu.cursosts.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekliuvekliu.cursosts.domain.Categoria;
import com.nekliuvekliu.cursosts.repositories.CategoriaRepository;
import com.nekliuvekliu.cursosts.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())); 		
	}

	public Categoria insert (Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
		
	public Categoria update (Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

}
