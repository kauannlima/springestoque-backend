package com.springestoque.springestoque_backend.domain.dto;

import com.springestoque.springestoque_backend.domain.Cargo;

public record CargoBodyDTO(
        Long id,
        String nome
) {


    public CargoBodyDTO(Cargo cargo) {
        this(
                cargo.getId(),
                cargo.getNome()
        );
    }

}
