package com.springestoque.springestoque_backend.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springestoque.springestoque_backend.domain.Categoria;
import com.springestoque.springestoque_backend.domain.Fornecedor;
import com.springestoque.springestoque_backend.domain.Produto;

public record ProdutoDTO(
        Long id,
        String nome,
        @JsonProperty("categoria_id")
        Long categoria,
        String descricao,
        @JsonProperty("quantidade_em_estoque")
        Integer quantidadeEmEstoque,
        @JsonProperty("fornecedor_id")
        Long fornecedor
) {
    public ProdutoDTO(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getCategoria().getId(),
                produto.getDescricao(),
                produto.getQuantidadeEmEstoque(),
                produto.getFornecedor().getId()
        );
    }
}
