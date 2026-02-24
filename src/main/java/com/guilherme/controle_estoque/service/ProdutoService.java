package com.guilherme.controle_estoque.service;


import com.guilherme.controle_estoque.dto.VendaRequest;
import com.guilherme.controle_estoque.exception.EstoqueInsuficienteException;
import com.guilherme.controle_estoque.exception.ProdutoNaoEncontradoException;
import com.guilherme.controle_estoque.model.ProdutoModel;
import com.guilherme.controle_estoque.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

  private final ProdutoRepository repository;
  public ProdutoService(ProdutoRepository repository){
      this.repository = repository;
  }

  public ProdutoModel salvar(ProdutoModel produto){
      if (produto.getPreco().compareTo(BigDecimal.ZERO) <= 0){
        throw new IllegalArgumentException("Preço deve ser maior do que zero.");
      }
      if (produto.getQuantidadeEmEstoque() < 0){
          throw new IllegalArgumentException("Quantidade deve ser maior do que zero");
      }
      if (produto.getNome() == null){
          throw new IllegalArgumentException("Nome não pode ser nulo");
      }
      produto.setAtivo(true);
      return repository.save(produto);
  }

  public List<ProdutoModel> listar(){
      return repository.findAll();
  }

  public ProdutoModel buscarPorId(Long id){
      return repository.findById(id)
              .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));
  }

  public ProdutoModel atualizar(Long id,ProdutoModel produtoAtualizado){
      ProdutoModel produtoExistente = buscarPorId(id);
      if (produtoAtualizado.getQuantidadeEmEstoque() < 0){
          throw new IllegalArgumentException("Não pode atualizar estoque para valor negativo.");
      }
      produtoExistente.setNome(produtoAtualizado.getNome());
      produtoExistente.setCategoria(produtoAtualizado.getCategoria());
      produtoExistente.setPreco(produtoAtualizado.getPreco());
      produtoExistente.setQuantidadeEmEstoque(produtoAtualizado.getQuantidadeEmEstoque());
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
