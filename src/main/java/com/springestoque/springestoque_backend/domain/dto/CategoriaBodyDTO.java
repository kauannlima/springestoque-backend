package com.springestoque.springestoque_backend.domain.dto;

import com.springestoque.springestoque_backend.domain.Categoria;

public record CategoriaBodyDTO(
        Long id,
        String nome,
        String descricao
) {

    public CategoriaBodyDTO(Categoria categoria) {
        this(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao()
        );
    }
}
