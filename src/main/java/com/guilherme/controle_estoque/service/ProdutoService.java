package com.guilherme.controle_estoque.service;


import com.guilherme.controle_estoque.dto.ProdutoRequest;
import com.guilherme.controle_estoque.dto.VendaRequest;
import com.guilherme.controle_estoque.exception.EstoqueInsuficienteException;
import com.guilherme.controle_estoque.exception.ProdutoNaoEncontradoException;
import com.guilherme.controle_estoque.model.CategoriaModel;
import com.guilherme.controle_estoque.model.ProdutoModel;
import com.guilherme.controle_estoque.repository.CategoriaRepository;
import com.guilherme.controle_estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

  private final ProdutoRepository repository;
  private final CategoriaRepository categoriaRepository;
  public ProdutoService(ProdutoRepository repository, CategoriaRepository categoriaRepository){
      this.repository = repository;
      this.categoriaRepository = categoriaRepository;
  }

  public ProdutoModel salvar(ProdutoRequest request){
      CategoriaModel categoria = categoriaRepository.findById(request.categoriaId())
              .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
      if (request.preco().compareTo(BigDecimal.ZERO) <= 0){
        throw new IllegalArgumentException("Preço deve ser maior do que zero.");
      }
      if (request.quantidadeEmEstoque() < 0){
          throw new IllegalArgumentException("Quantidade deve ser maior do que zero");
      }
      if (request.nome() == null){
          throw new IllegalArgumentException("Nome não pode ser nulo");
      }
      ProdutoModel produto = new ProdutoModel();
      produto.setNome(request.nome());
      produto.setPreco(request.preco());
      produto.setQuantidadeEmEstoque(request.quantidadeEmEstoque());
      produto.setCategoria(categoria);
      produto.setAtivo(request.quantidadeEmEstoque() > 0);
      return repository.save(produto);
  }

  public List<ProdutoModel> listar(){
      return repository.findAll();
  }

  public ProdutoModel buscarPorId(Long id){
      return repository.findById(id)
              .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));
  }

  public ProdutoModel atualizar(Long id,ProdutoRequest request){
      ProdutoModel produtoExistente = buscarPorId(id);
      CategoriaModel categoria = categoriaRepository.findById(request.categoriaId())
              .orElseThrow(() -> new RuntimeException("Categoria não encontrada."));
      if (request.quantidadeEmEstoque() < 0){
          throw new IllegalArgumentException("Não pode atualizar estoque para valor negativo.");
      }
      produtoExistente.setNome(request.nome());
      produtoExistente.setCategoria(categoria);
      produtoExistente.setPreco(request.preco());
      produtoExistente.setQuantidadeEmEstoque(request.quantidadeEmEstoque());
      produtoExistente.setAtivo(produtoExistente.getQuantidadeEmEstoque() > 0);
      return repository.save(produtoExistente);
  }

  public void deletar(Long id){
      ProdutoModel produtoExistente = buscarPorId(id);
      repository.delete(produtoExistente);
  }

  public ProdutoModel vender(Long id, VendaRequest request){
      ProdutoModel produto = buscarPorId(id);
      if (!produto.getAtivo()){
          throw new IllegalArgumentException("Produto está inativo.");
      }
      if (request.quantidade() <= 0){
          throw new IllegalArgumentException("A quantidade não pode ser menor ou igual a zero.");
      }
      if (request.quantidade() > produto.getQuantidadeEmEstoque()){
          throw new EstoqueInsuficienteException("Estoque insuficiente.");
      }
      produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - request.quantidade());
      if (produto.getQuantidadeEmEstoque() == 0){
          produto.setAtivo(false);
      }
      return repository.save(produto);
  }
}
