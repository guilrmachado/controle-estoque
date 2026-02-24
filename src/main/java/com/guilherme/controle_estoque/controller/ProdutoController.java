package com.guilherme.controle_estoque.controller;
import com.guilherme.controle_estoque.dto.ProdutoRequest;
import com.guilherme.controle_estoque.dto.VendaRequest;
import com.guilherme.controle_estoque.model.ProdutoModel;
import com.guilherme.controle_estoque.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;
    public ProdutoController(ProdutoService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProdutoModel> criarProduto(@RequestBody ProdutoRequest request) {
        ProdutoModel novoProduto = service.salvar(request);
        return ResponseEntity.status(201).body(novoProduto);
    }

    @PostMapping("/{id}/vender")
    public ResponseEntity<ProdutoModel> venderProduto(@PathVariable Long id, @RequestBody @Valid VendaRequest request) {
        ProdutoModel produto = service.vender(id,request);
        return ResponseEntity.ok(produto);
    }


    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listarProdutos() {
        List<ProdutoModel> produtos = service.listar();
        return ResponseEntity.ok(produtos);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> buscarProduto(@PathVariable Long id) {
        ProdutoModel produto = service.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarProduto(@PathVariable Long id, @RequestBody ProdutoRequest request) {
        ProdutoModel produtoExistente = service.atualizar(id,request);
        return ResponseEntity.ok(produtoExistente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarProduto(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
