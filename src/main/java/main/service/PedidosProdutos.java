package main.service;

import java.util.List;

import main.model.Produto;

public class PedidosProdutos {
	
	
	public void recebePedido(List<Produto> produto, String nomeProduto, Integer quantidade, Double saldo) {
		
		Produto p = this.verificaNome(produto, nomeProduto);
			
			if(this.verificaQuantidade(p.getQuantidade() , quantidade)) {
				if(this.verificaCalculoValor(p.getValor(), quantidade, saldo)) {
					p.setQuantidade(p.getQuantidade() - quantidade);
				}
			}	
	} 
	
	public Produto verificaNome(List<Produto> produto, String nomeProdutoPedido) {
		
		for(Produto p:produto) {
			if(p.getNomeProduto() == nomeProdutoPedido) {
				return p;
			}
		}
		
		throw new IllegalArgumentException("Produto não existe");
	}
	
	public Boolean verificaQuantidade(Integer quantidadeAtual, Integer quantidadePedida) {
		if(quantidadeAtual >= quantidadePedida) {
			return true;
		}
		throw new IllegalArgumentException("Quantidade pedida não bate com a quantidade atual");
	}
	
	public Boolean verificaCalculoValor(Double valor, Integer quantidade, Double saldo) {
		if(this.calculaValorQuantidade(valor, quantidade) <= saldo) {
			System.out.println("Sucesso");
			return true;
		}
		throw new IllegalArgumentException("Valor não compativel com o preço do produto");
	}
	
	private Double calculaValorQuantidade(Double valor, Integer quantidade) {
		return valor * quantidade;
	}
}