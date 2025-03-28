package com.springestoque.springestoque_backend.exception;

public class StatusManutencaoJaAlteradoException extends RuntimeException {
    public StatusManutencaoJaAlteradoException(Long id, String statusAtual) {
        super("A manutenção de ID " + id + " já foi alterada para " + statusAtual + " e não pode ser modificada novamente.");
    }
}
