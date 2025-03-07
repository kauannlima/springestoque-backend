package com.springestoque.springestoque_backend.domain.dto;

import com.springestoque.springestoque_backend.domain.Categoria;

public record CategoriaDTO(
        Long id,
        String nome,
        String descricao
) {

    public CategoriaDTO(Categoria categoria) {
        this(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao()
        );
    }
}
