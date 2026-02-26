package com.guilherme.controle_estoque.service;


import com.guilherme.controle_estoque.dto.CategoriaRequest;
import com.guilherme.controle_estoque.model.CategoriaModel;
import com.guilherme.controle_estoque.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }


    public CategoriaModel salvar(CategoriaRequest request){
        Optional<CategoriaModel> categoriaExistente = categoriaRepository.findByNome(request.nome());
        if (categoriaExistente.isPresent()){
            throw new RuntimeException("Já existe uma categoria com esse nome.");
        }
        CategoriaModel categoria = new CategoriaModel();
        categoria.setNome(request.nome());
        categoria.setAtivo(true);
        return categoriaRepository.save(categoria);
    }

    public List<CategoriaModel> listar(){
        return categoriaRepository.findAll();
    }

    public void deletar(Long id){
        CategoriaModel categoriaExcluida = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        categoriaRepository.delete(categoriaExcluida);
    }
}
