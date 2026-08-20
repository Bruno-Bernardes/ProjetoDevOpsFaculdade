package main.model;

public class Produto {
	private String nomeProduto;
	private Double valor;
	private Integer quantidade;
	private String categoria;
	
	public Produto() {
	}

	public Produto(String nomeProduto, Double valor, Integer quantidade, String categoria) {
		this.nomeProduto = nomeProduto;
		this.valor = valor;
		this.quantidade = this.quantidadeNaoPodeSerNegativo(quantidade);
		this.categoria = categoria;
	}
	//teste
	private Integer quantidadeNaoPodeSerNegativo(Integer quantidade) {
		
		if(quantidade >= 0) {
			return quantidade;
		}else {
			throw new IllegalArgumentException("Argumento inválido");
		}
		
	}
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = this.quantidadeNaoPodeSerNegativo(quantidade);
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	
	@Override
	public String toString() {
		return "Produto [nomeProduto=" + nomeProduto + ", valor=" + valor + ", quantidade=" + quantidade
				+ ", categoria=" + categoria + "]";
	}
	
}