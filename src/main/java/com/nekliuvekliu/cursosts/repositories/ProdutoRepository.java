package com.nekliuvekliu.cursosts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nekliuvekliu.cursosts.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
		
}
