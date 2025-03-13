package com.springestoque.springestoque_backend.exception;

public class FuncionarioNaoEncontradoException extends RuntimeException {
    public FuncionarioNaoEncontradoException(Long id) {
        super("Funcionario não encontrado com o ID: " + id);
    }

    public FuncionarioNaoEncontradoException(String nome) {
        super("Fornecedor não encontrado com o nome: " + nome);
    }
}
