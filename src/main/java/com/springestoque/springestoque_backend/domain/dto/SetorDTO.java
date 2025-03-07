package com.springestoque.springestoque_backend.domain.dto;

import com.springestoque.springestoque_backend.domain.Setor;

public record SetorDTO(
        Long id,
        String nome
) {

    public SetorDTO(Setor setor) {
        this(
                setor.getId(),
                setor.getNome()
        );
    }
}
