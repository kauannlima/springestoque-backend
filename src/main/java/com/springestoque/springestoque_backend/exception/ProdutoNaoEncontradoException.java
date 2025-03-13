package com.springestoque.springestoque_backend.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(Long id) {
        super("Produto não encontrado com o ID: " + id);
    }

    public ProdutoNaoEncontradoException(String nome) {
        super("Produto não encontrado com o nome: " + nome);
    }
}
