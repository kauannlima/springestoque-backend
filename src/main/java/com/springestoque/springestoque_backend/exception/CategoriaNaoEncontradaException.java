package com.springestoque.springestoque_backend.exception;

public class CategoriaNaoEncontradaException extends RuntimeException {
    public CategoriaNaoEncontradaException(Long id) {
        super("Categoria não encontrada com o ID: " + id);
    }
    public CategoriaNaoEncontradaException(String nome) {
        super("Cargo não encontrada com o nome: " + nome);
    }
}
