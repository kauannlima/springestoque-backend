package com.springestoque.springestoque_backend.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(Long id) {
        super("Usuario não encontrado com o ID: " + id);
    }

    public UsuarioNaoEncontradoException(String nome) {
        super("Usuario não encontrado com o nome: " + nome);
    }
}
