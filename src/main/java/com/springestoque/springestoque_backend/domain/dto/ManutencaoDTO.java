package com.springestoque.springestoque_backend.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springestoque.springestoque_backend.domain.Manutencao;
import com.springestoque.springestoque_backend.domain.MovimentacaoEstoque;
import com.springestoque.springestoque_backend.domain.StatusManutencaoEnum;
import com.springestoque.springestoque_backend.domain.TipoMovimentacaoEnum;

import java.time.LocalDate;

public record ManutencaoDTO(
        Long id,
        @JsonProperty("produto_id")
        Long produto,
        @JsonProperty("quantidade_manutencao")
        Integer quantidadeManutencao,
        @JsonProperty("data_inicio")
        LocalDate dataInicio,
        @JsonProperty("data_fim")
        LocalDate dataFim,
        String descricao,
        StatusManutencaoEnum status

) {
    public ManutencaoDTO(Manutencao manutencao) {
        this(manutencao.getId(),
                manutencao.getProduto().getId(),
                manutencao.getQuantidadeManutencao(),
                manutencao.getDataInicio(),
                manutencao.getDataFim(),
                manutencao.getDescricao(),
                manutencao.getStatus());
    }
}

