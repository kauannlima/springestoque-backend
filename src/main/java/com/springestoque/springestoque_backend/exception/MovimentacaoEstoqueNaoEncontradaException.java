package com.springestoque.springestoque_backend.exception;

public class MovimentacaoEstoqueNaoEncontradaException extends RuntimeException {
    public MovimentacaoEstoqueNaoEncontradaException(Long id) {
        super("Movimentação não encontrada com o ID: " + id);
    }

}
