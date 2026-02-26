package com.guilherme.controle_estoque.service;

import com.guilherme.controle_estoque.dto.ProdutoRequest;
import com.guilherme.controle_estoque.model.CategoriaModel;
import com.guilherme.controle_estoque.model.ProdutoModel;
import com.guilherme.controle_estoque.repository.CategoriaRepository;
import com.guilherme.controle_estoque.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaServiceTest {
    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProdutoService produtoService;

    private ProdutoModel produto;
    private CategoriaModel categoria;

    @BeforeEach
    void setup() {
        categoria = new CategoriaModel();
        categoria.setId(1L);
        categoria.setNome("Eletrônicos");
        categoria.setAtivo(true);

        produto = new ProdutoModel();
        produto.setId(1L);
        produto.setNome("Notebook");
        produto.setPreco(BigDecimal.valueOf(3500));
        produto.setQuantidadeEmEstoque(10);
        produto.setCategoria(categoria);
        produto.setAtivo(true);

    }

    @Test
    void deveLancarExcecaoQuandoCategoriaNaoExistir() {

        ProdutoRequest request = new ProdutoRequest(
                "Notebook",
                1L,
                new BigDecimal(10.00),
                100
        );

        when(categoriaRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                produtoService.salvar(request)
        );

        verify(produtoRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoCategoriaEstiverInativa() {

        categoria.setAtivo(false);

        ProdutoRequest request = new ProdutoRequest(
                "Notebook",
                1L,
                new BigDecimal(10.00),
                100
        );

        when(categoriaRepository.findById(1L))
                .thenReturn(Optional.of(categoria));

        assertThrows(RuntimeException.class, () ->
                produtoService.salvar(request)
        );

        verify(produtoRepository, never()).save(any());
    }
}
