package com.springestoque.springestoque_backend.exception;

public class CargoNaoEncontradoException extends RuntimeException {
    public CargoNaoEncontradoException(Long id) {
        super("Cargo não encontrado com o ID: " + id);
    }
    public CargoNaoEncontradoException(String nome) {
        super("Cargo não encontrado com o nome: " + nome);
    }
}
