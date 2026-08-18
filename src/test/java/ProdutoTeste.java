import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import main.model.Produto;

public class ProdutoTeste {

    @Test
    void testeModelQuantidadeNaoPodeSerNegativo() {

        Produto produto = new Produto();

        assertThrows(IllegalArgumentException.class, () -> {
            produto.setQuantidade(-1);
        });
    }
    
 

    
}