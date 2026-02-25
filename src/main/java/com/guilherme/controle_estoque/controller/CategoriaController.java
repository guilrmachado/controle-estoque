package com.guilherme.controle_estoque.controller;

import com.guilherme.controle_estoque.dto.CategoriaRequest;
import com.guilherme.controle_estoque.dto.ProdutoRequest;
import com.guilherme.controle_estoque.model.CategoriaModel;
import com.guilherme.controle_estoque.model.ProdutoModel;
import com.guilherme.controle_estoque.repository.CategoriaRepository;
import com.guilherme.controle_estoque.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;
    public CategoriaController(CategoriaService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<CategoriaModel>> listarCategorias(){
        List<CategoriaModel> categorias = service.listar();
        return ResponseEntity.ok(categorias);
    }

    @PostMapping
    public ResponseEntity<CategoriaModel> criarCategoria(@RequestBody CategoriaRequest request) {
        CategoriaModel novaCategoria = service.salvar(request);
        return ResponseEntity.status(201).body(novaCategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaModel> deletarCategoria(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
