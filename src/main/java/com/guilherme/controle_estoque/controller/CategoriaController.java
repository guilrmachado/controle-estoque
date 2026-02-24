package com.guilherme.controle_estoque.controller;

import com.guilherme.controle_estoque.model.CategoriaModel;
import com.guilherme.controle_estoque.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    @GetMapping
    public List<CategoriaModel> listar(){
        return categoriaRepository.findAll();
    }
}
