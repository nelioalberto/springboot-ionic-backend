package com.nekliuvekliu.cursosts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nekliuvekliu.cursosts.domain.Cidade;
import com.nekliuvekliu.cursosts.domain.Cliente;
import com.nekliuvekliu.cursosts.domain.Endereco;
import com.nekliuvekliu.cursosts.dto.ClienteDTO;
import com.nekliuvekliu.cursosts.dto.ClienteNewDTO;
import com.nekliuvekliu.cursosts.enums.TipoCliente;
import com.nekliuvekliu.cursosts.repositories.ClienteRepository;
import com.nekliuvekliu.cursosts.repositories.EnderecoRepository;
import com.nekliuvekliu.cursosts.services.exceptions.DataIntegrityException;
import com.nekliuvekliu.cursosts.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository; 
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id); 
		return obj.orElseThrow(() -> new ObjectNotFoundException( 
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())); 		
	}

	@Transactional
	public Cliente insert (Cliente obj) {
		obj.setId(null);
		repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
		
	public Cliente update (Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete (Integer id) {
		find(id);
		try {
		repo.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir cliente com entidades relacionadas");
		}
	}
	
	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesForPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesForPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
		}
		
	public Cliente fromDTO (ClienteDTO objDTO) {
	//	throw new UnsupportedOperationException();
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}

	public Cliente fromDTO (ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, 
				                  objDTO.getNome(), 
				                  objDTO.getEmail(), 
				                  objDTO.getCpfOuCnpj(), 
				                  TipoCliente.toEnum(objDTO.getTipo()));
		
		Cidade cid = new Cidade (objDTO.getCidadeId(), null, null);
				
		Endereco end = new Endereco(null, 
				           objDTO.getLogradouro(), 
				           objDTO.getNumero(), 
				           objDTO.getComplemento(), 
				           objDTO.getBairro(), 
				           objDTO.getCep(), 
				           cli, cid);
		
		cli.getEnderecos().add(end);
		
		cli.getTelefones().add(objDTO.getTelefone1());
		if (objDTO.getTelefone2()!=null) {
			cli.getTelefones().add(objDTO.getTelefone2());			
		}
		if (objDTO.getTelefone3()!=null) {
			cli.getTelefones().add(objDTO.getTelefone3());			
		}
		return cli;
	}

	private void updateData (Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	
}
