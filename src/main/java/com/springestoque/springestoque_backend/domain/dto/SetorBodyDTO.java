package com.springestoque.springestoque_backend.domain.dto;

import com.springestoque.springestoque_backend.domain.Setor;

public record SetorBodyDTO(
        Long id,
        String nome
) {

    public SetorBodyDTO(Setor setor) {
        this(
                setor.getId(),
                setor.getNome()
        );
    }
}
