package main.service;

import java.util.List;

import main.model.Produto;

public class PedidosProdutos {
	
	
	public void recebePedido(List<Produto> produto, String nomeProduto, Integer quantidade, Double saldo) {
		
		for(Produto p : produto) {
			if(p.getNomeProduto() == nomeProduto) {
				if(p.getQuantidade() >= quantidade) {
					if(this.calculaValorQuantidade(p.getValor(), quantidade) <= saldo) {
						p.setQuantidade(p.getQuantidade() - quantidade);
						System.out.println("Sucesso");
					}else {
						System.out.println("Valor não compativel com o preço do produto");
					}
				}else {
					System.out.println("Quantidade não suficiente");
				}
			}else {
				System.out.println("Produto não encontrado");
			}
		}
	} 
	
	private Double calculaValorQuantidade(Double valor, Integer quantidade) {
		return valor * quantidade;
	}
}