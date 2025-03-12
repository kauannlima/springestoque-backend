package com.springestoque.springestoque_backend.domain.dto;

import com.springestoque.springestoque_backend.domain.Fornecedor;

public record FornecedorBodyDTO(
        String nome,
        String endereco,
        String telefone,
        String email
) {

    public FornecedorBodyDTO(Fornecedor fornecedor) {
        this(
                fornecedor.getNome(),
                fornecedor.getEndereco(),
                fornecedor.getTelefone(),
                fornecedor.getEmail()
        );
    }

}
