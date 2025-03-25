package com.springestoque.springestoque_backend.domain.dto;

import com.springestoque.springestoque_backend.domain.MovimentacaoEstoque;

import java.time.LocalDate;

public record MovimentacaoEstoqueDTO(
        Long id,
        Long produto,
        Integer quantidadeMovimentada,
        String tipoDeMovimentacao,
        LocalDate dataDaMovimentacao,
        Long funcionario,
        Long setorDestino
) {
    public MovimentacaoEstoqueDTO(MovimentacaoEstoque movimentacaoEstoque) {
        this(movimentacaoEstoque.getId(),
                movimentacaoEstoque.getProduto().getId(),
                movimentacaoEstoque.getQuantidadeMovimentada(),
                movimentacaoEstoque.getTipoDeMovimentacao().getTipo(),  // Agora pega o valor do tipo do Enum
                movimentacaoEstoque.getDataDaMovimentacao(),
                movimentacaoEstoque.getFuncionario().getId(),
                movimentacaoEstoque.getSetor().getId());
    }
}

