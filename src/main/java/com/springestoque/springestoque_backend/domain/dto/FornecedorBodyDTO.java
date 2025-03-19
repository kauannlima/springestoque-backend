package com.springestoque.springestoque_backend.domain.dto;

import com.springestoque.springestoque_backend.domain.Fornecedor;

public record FornecedorBodyDTO(
        Long id,
        String nome,
        String endereco,
        String telefone,
        String email
) {

    public FornecedorBodyDTO(Fornecedor fornecedor) {
        this(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getEndereco(),
                fornecedor.getTelefone(),
                fornecedor.getEmail()
        );
    }

}
