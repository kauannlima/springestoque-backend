package com.springestoque.springestoque_backend.domain.dto;

import com.springestoque.springestoque_backend.domain.Cargo;

public record CargoBodyDTO(
        String nome
) {


    public CargoBodyDTO(Cargo cargo) {
        this(
                cargo.getNome()
        );
    }

}
