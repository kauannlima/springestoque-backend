package com.springestoque.springestoque_backend.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springestoque.springestoque_backend.domain.Manutencao;
import com.springestoque.springestoque_backend.domain.MovimentacaoEstoque;
import com.springestoque.springestoque_backend.domain.StatusManutencaoEnum;

import java.time.LocalDate;

public record ManutencaoBodyDTO(
        Long id,
        String produto,
        Integer quantidadeManutencao,
        LocalDate dataInicio,
        LocalDate dataFim,
        String descricao,
        String status

) {
    public ManutencaoBodyDTO(Manutencao manutencao) {
        this(manutencao.getId(),
                manutencao.getProduto().getNome(),
                manutencao.getQuantidadeManutencao(),
                manutencao.getDataInicio(),
                manutencao.getDataFim(),
                manutencao.getDescricao(),
                manutencao.getStatus().getStatus());
    }

}


