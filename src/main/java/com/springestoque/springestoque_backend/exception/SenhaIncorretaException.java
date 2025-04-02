package com.springestoque.springestoque_backend.exception;

public class SenhaIncorretaException extends RuntimeException {

    public SenhaIncorretaException() {
        super("Senha incorreta.");
    }
}
