package com.springestoque.springestoque_backend.exception;

public class EstoqueInsuficienteException extends RuntimeException {

    public EstoqueInsuficienteException(Long produtoId, Integer quantidadeEstoque) {
        super("Estoque insuficiente para o produto com ID: " + produtoId + ". Estoque atual: " + quantidadeEstoque);
    }
}
