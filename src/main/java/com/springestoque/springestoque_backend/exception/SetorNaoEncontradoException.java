package com.springestoque.springestoque_backend.exception;

public class SetorNaoEncontradoException extends RuntimeException {
    public SetorNaoEncontradoException(Long id) {
        super("Setor não encontrado com o ID: " + id);
    }

    public SetorNaoEncontradoException(String nome) {
        super("Setor não encontrado com o nome: " + nome);
    }
}
