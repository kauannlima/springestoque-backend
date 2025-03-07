package com.springestoque.springestoque_backend.domain.dto;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.Setor;

public record CargoDTO(
        Long id,
        String nome
) {


    public CargoDTO(Cargo cargo) {
        this(
                cargo.getId(),
                cargo.getNome()
        );
    }

}
