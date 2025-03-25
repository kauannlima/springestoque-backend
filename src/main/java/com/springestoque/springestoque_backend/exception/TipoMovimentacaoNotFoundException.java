package com.springestoque.springestoque_backend.exception;

public class TipoMovimentacaoNotFoundException extends RuntimeException {

    // Construtor com mensagem personalizada
    public TipoMovimentacaoNotFoundException(String tipo) {
        super("Tipo de movimentação não encontrado: " + tipo);
    }

    // Construtor com mensagem e causa (opcional)
    public TipoMovimentacaoNotFoundException(String tipo, Throwable cause) {
        super("Tipo de movimentação não encontrado: " + tipo, cause);
    }
}
