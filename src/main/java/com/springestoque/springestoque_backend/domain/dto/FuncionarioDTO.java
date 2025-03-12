package com.springestoque.springestoque_backend.domain.dto;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.Funcionario;

public record FuncionarioDTO(
        Long id,
        String nome,
        Long cargo,
        String email,
        String telefone
) {

    public FuncionarioDTO(Funcionario funcionario) {
        this(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCargo().getId(),
                funcionario.getEmail(),
                funcionario.getTelefone()
        );
    }

}
