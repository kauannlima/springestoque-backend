package com.springestoque.springestoque_backend.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springestoque.springestoque_backend.domain.Fornecedor;

public record FornecedorDTO(
        Long id,
        String nome,
        String endereco,
        String telefone,
        String email
) {

    public FornecedorDTO(Fornecedor fornecedor) {
        this(
                fornecedor.getId(),
                fornecedor.getNome(),
                fornecedor.getEndereco(),
                fornecedor.getTelefone(),
                fornecedor.getEmail()
        );
    }

}
