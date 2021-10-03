package com.nekliuvekliu.cursosts;

import java.text.SimpleDateFormat;
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
import com.nekliuvekliu.cursosts.domain.ItemPedido;
import com.nekliuvekliu.cursosts.domain.Pagamento;
import com.nekliuvekliu.cursosts.domain.PagamentoComBoleto;
import com.nekliuvekliu.cursosts.domain.PagamentoComCartao;
import com.nekliuvekliu.cursosts.domain.Pedido;
import com.nekliuvekliu.cursosts.domain.Produto;
import com.nekliuvekliu.cursosts.enums.EstadoPagamento;
import com.nekliuvekliu.cursosts.enums.TipoCliente;
import com.nekliuvekliu.cursosts.repositories.CategoriaRepository;
import com.nekliuvekliu.cursosts.repositories.CidadeRepository;
import com.nekliuvekliu.cursosts.repositories.ClienteRepository;
import com.nekliuvekliu.cursosts.repositories.EnderecoRepository;
import com.nekliuvekliu.cursosts.repositories.EstadoRepository;
import com.nekliuvekliu.cursosts.repositories.ItemPedidoRepository;
import com.nekliuvekliu.cursosts.repositories.PagamentoRepository;
import com.nekliuvekliu.cursosts.repositories.PedidoRepository;
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria (null, "informática");
		Categoria cat2 = new Categoria (null, "Escritório");
		Categoria cat3 = new Categoria (null, "Cama, Mesa e Banho");
		Categoria cat4 = new Categoria (null, "Eletrônicos");
		Categoria cat5 = new Categoria (null, "Jardinagem");
		Categoria cat6 = new Categoria (null, "Decoração");
		Categoria cat7 = new Categoria (null, "Perfumaria");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2, cat3, cat4, cat5, cat6, cat7));
		
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:33"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:33"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 19:33"), null);
		ped2.setPagamento(pagto2);		
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}
