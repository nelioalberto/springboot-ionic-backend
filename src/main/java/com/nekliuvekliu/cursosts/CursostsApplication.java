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

	@Override
	public void run(String... args) throws Exception {
		
	}

}
