package com.springestoque.springestoque_backend.domain.dto;

import com.springestoque.springestoque_backend.domain.MovimentacaoEstoque;

import java.time.LocalDate;

public record MovimentacaoEstoqueBodyDTO(
        Long id,
        String produto,
        Integer quantidadeMovimentada,
        String tipoDeMovimentacao,
        LocalDate dataDaMovimentacao,
        String funcionario,
        String setorDestino
) {
    public MovimentacaoEstoqueBodyDTO(MovimentacaoEstoque movimentacaoEstoque) {
        this(movimentacaoEstoque.getId(),
                movimentacaoEstoque.getProduto().getNome(),
                movimentacaoEstoque.getQuantidadeMovimentada(),
                movimentacaoEstoque.getTipoDeMovimentacao().getTipo(),  // Agora pega o valor do tipo do Enum
                movimentacaoEstoque.getDataDaMovimentacao(),
                movimentacaoEstoque.getFuncionario().getNome(),
                movimentacaoEstoque.getSetor().getNome());
    }
}

