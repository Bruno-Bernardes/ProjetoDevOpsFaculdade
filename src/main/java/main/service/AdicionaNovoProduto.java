package main.service;

import java.util.ArrayList;
import java.util.List;

import main.model.Produto;

public class AdicionaNovoProduto {
	private static List<Produto> produto = new ArrayList<>();
	
	
	public void adicionaNovosProdutos(String nomeProduto, Double valor, Integer quantidade, String categoria) {
		
		this.produto.add(new Produto(nomeProduto, valor, quantidade, categoria));
		
	}
	
	public List<Produto> retornaProdutos(){
		return this.produto;
	}
	 
}