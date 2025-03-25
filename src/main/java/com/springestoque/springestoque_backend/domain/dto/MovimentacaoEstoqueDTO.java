package com.springestoque.springestoque_backend.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springestoque.springestoque_backend.domain.MovimentacaoEstoque;
import com.springestoque.springestoque_backend.domain.TipoMovimentacaoEnum;

import java.time.LocalDate;

public record MovimentacaoEstoqueDTO(
        Long id,
        @JsonProperty("produto_id")
        Long produto,
        Integer quantidadeMovimentada,
        TipoMovimentacaoEnum tipoDeMovimentacao,
        LocalDate dataDaMovimentacao,
        @JsonProperty("funcionario_id")
        Long funcionario,
        @JsonProperty("setor_id")
        Long setorDestino
) {
    public MovimentacaoEstoqueDTO(MovimentacaoEstoque movimentacaoEstoque) {
        this(movimentacaoEstoque.getId(),
                movimentacaoEstoque.getProduto().getId(),
                movimentacaoEstoque.getQuantidadeMovimentada(),
                movimentacaoEstoque.getTipoDeMovimentacao(),  // Agora pega o valor do tipo do Enum
                movimentacaoEstoque.getDataDaMovimentacao(),
                movimentacaoEstoque.getFuncionario().getId(),
                movimentacaoEstoque.getSetor().getId());
    }
}

