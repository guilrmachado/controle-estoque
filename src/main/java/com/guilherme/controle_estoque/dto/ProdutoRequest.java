package com.guilherme.controle_estoque.dto;

import java.math.BigDecimal;

public record ProdutoRequest(
        String nome,
        Long categoriaId,
        BigDecimal preco,
        Integer quantidadeEmEstoque
) {
}
