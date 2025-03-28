package com.springestoque.springestoque_backend.exception;

public class StatusManutencaoInvalidoException extends RuntimeException {

    public StatusManutencaoInvalidoException(String status) {
        super("O status de manutenção fornecido é inválido: " + status);
    }
}
