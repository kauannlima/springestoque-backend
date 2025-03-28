package com.springestoque.springestoque_backend.exception;

public class EstoqueInsuficienteException extends RuntimeException {

    public EstoqueInsuficienteException(Long produtoId, Integer quantidadeEstoque, Integer quantidadeMovimentada) {
        super("Estoque insuficiente para o produto com ID: " + produtoId + ". Estoque atual: " + quantidadeEstoque + ". Estoque que você pretende movimentar: " + quantidadeMovimentada);
    }

    public EstoqueInsuficienteException(Integer quantidadeEstoque, Integer quantidadeMovimentada) {
        super("Estoque insuficiente para excluir movimentação de entrada. Estoque atual: " + quantidadeEstoque + ". Quantidade cancelar: " + quantidadeMovimentada);
    }
    public EstoqueInsuficienteException(Long produtoId, Integer quantidadeEstoque) {
        super("Estoque insuficiente para realizar a manutenção do produto com ID: " + produtoId + ". Estoque atual: " + quantidadeEstoque);
    }
}
