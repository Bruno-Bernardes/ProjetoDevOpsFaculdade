import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import main.model.Produto;
import main.service.PedidosProdutos;

public class ProdutoTeste {

    @Test
    void testeModelQuantidadeNaoPodeSerNegativo() {

        Produto produto = new Produto();

        assertThrows(IllegalArgumentException.class, () -> {
            produto.setQuantidade(-1);
        });
    }
    
    @Test
    void verificaSeNomeGeraErroQuandoErrado() {
    	List<Produto> produto = new ArrayList<>();
    	
    	produto.add(new Produto("Cadeira", 100.00, 5, "Movel"));
    	produto.add(new Produto("Cadeiras", 100.00, 5, "Movel"));
    	produto.add(new Produto("Mesas", 100.00, 5, "Movel"));
    	
    	PedidosProdutos pedido = new PedidosProdutos();
    	String nomeCorreto = pedido.verificaNome(produto, "Cadeira").getNomeProduto();
    	
    	assertEquals("Cadeirass", nomeCorreto);
    	
    	
    }
    @Test
    void verificaSeNomeEigual() {
    	List<Produto> produto = new ArrayList<>();
    	
    	produto.add(new Produto("Cadeira", 100.00, 5, "Movel"));
    	produto.add(new Produto("Cadeiras", 100.00, 5, "Movel"));
    	produto.add(new Produto("Mesas", 100.00, 5, "Movel"));
    	
    	PedidosProdutos pedido = new PedidosProdutos();
    	String nomeCorreto = pedido.verificaNome(produto, "Cadeira").getNomeProduto();
    	
    	assertEquals("Cadeira", nomeCorreto);
    	
    	
    	
    }
 

    
}