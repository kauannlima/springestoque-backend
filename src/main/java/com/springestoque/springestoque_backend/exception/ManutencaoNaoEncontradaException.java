package com.springestoque.springestoque_backend.exception;

public class ManutencaoNaoEncontradaException extends RuntimeException {
    public ManutencaoNaoEncontradaException(Long id) {
        super("Manutençao não encontrada com o ID: " + id);
    }

}
