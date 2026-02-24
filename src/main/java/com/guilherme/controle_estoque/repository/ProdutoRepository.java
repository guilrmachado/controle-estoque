package com.guilherme.controle_estoque.repository;

import com.guilherme.controle_estoque.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoModel,Long> {
}
