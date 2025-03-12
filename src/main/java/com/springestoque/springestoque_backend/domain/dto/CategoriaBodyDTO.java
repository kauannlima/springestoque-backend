package com.springestoque.springestoque_backend.domain.dto;

import com.springestoque.springestoque_backend.domain.Categoria;

public record CategoriaBodyDTO(
        String nome,
        String descricao
) {

    public CategoriaBodyDTO(Categoria categoria) {
        this(
                categoria.getNome(),
                categoria.getDescricao()
        );
    }
}
