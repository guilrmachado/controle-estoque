package com.guilherme.controle_estoque.model;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria")
public class CategoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nome;
    @Column(nullable = false)
    private Boolean ativo;


    public CategoriaModel() {
    }

    public CategoriaModel(Long id, String nome, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}
