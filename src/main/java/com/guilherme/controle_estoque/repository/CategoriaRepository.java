package com.guilherme.controle_estoque.repository;

import com.guilherme.controle_estoque.model.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<CategoriaModel, Long> {
    Optional<CategoriaModel> findByNome(String nome);
}
