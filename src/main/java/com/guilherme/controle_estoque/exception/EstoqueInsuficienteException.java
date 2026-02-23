package com.guilherme.controle_estoque.exception;

public class EstoqueInsuficienteException extends RuntimeException {

    public EstoqueInsuficienteException(String mensagem){
        super(mensagem);
    }
}
