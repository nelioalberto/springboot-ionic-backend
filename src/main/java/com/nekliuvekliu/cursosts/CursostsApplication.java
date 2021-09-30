package com.nekliuvekliu.cursosts;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nekliuvekliu.cursosts.domain.Categoria;
import com.nekliuvekliu.cursosts.domain.Cidade;
import com.nekliuvekliu.cursosts.domain.Cliente;
import com.nekliuvekliu.cursosts.domain.Endereco;
import com.nekliuvekliu.cursosts.domain.Estado;
import com.nekliuvekliu.cursosts.domain.Produto;
import com.nekliuvekliu.cursosts.enums.TipoCliente;
import com.nekliuvekliu.cursosts.repositories.CategoriaRepository;
import com.nekliuvekliu.cursosts.repositories.CidadeRepository;
import com.nekliuvekliu.cursosts.repositories.ClienteRepository;
import com.nekliuvekliu.cursosts.repositories.EnderecoRepository;
import com.nekliuvekliu.cursosts.repositories.EstadoRepository;
import com.nekliuvekliu.cursosts.repositories.ProdutoRepository;

@SpringBootApplication
public class CursostsApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(CursostsApplication.class, args);
	}

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria (null, "informática");
		Categoria cat2 = new Categoria (null, "Escritório");
	
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		
		Produto p1 = new Produto (null, "Computador", 2000.00);
		Produto p2 = new Produto (null, "Impressora", 800.00);
		Produto p3 = new Produto (null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p1, p3));
		cat2.getProdutos().addAll(Arrays.asList(p1));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2= new Cidade(null, "São Paulo", est2);
		Cidade c3= new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "mariasilva@gmail.com", "1111111111", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("21 11111-1111", "21 22222-2222"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "360", "apto 202", "Jardim", "20541340", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua MiraFlores", "777", "apto 303", "MuchoFlorida", "12345123", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		
	}

}
