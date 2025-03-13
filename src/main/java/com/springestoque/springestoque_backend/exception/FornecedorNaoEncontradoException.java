package com.springestoque.springestoque_backend.exception;

public class FornecedorNaoEncontradoException extends RuntimeException {
    public FornecedorNaoEncontradoException(Long id) {
        super("Fornecedor não encontrado com o ID: " + id);
    }
    public FornecedorNaoEncontradoException(String nome) {
        super("Fornecedor não encontrado com o nome: " + nome);
    }
}
