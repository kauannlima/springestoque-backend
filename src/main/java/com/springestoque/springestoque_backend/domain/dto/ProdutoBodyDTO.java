package com.springestoque.springestoque_backend.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springestoque.springestoque_backend.domain.Produto;

public record ProdutoBodyDTO(
        String nome,
        String categoria,
        String descricao,
        Integer quantidadeEmEstoque,
        String fornecedor
) {
    public ProdutoBodyDTO(Produto produto) {
        this(
                produto.getNome(),
                produto.getCategoria().getNome(),
                produto.getDescricao(),
                produto.getQuantidadeEmEstoque(),
                produto.getFornecedor().getNome()
        );
    }
}
