package com.springestoque.springestoque_backend.domain.dto;

import com.springestoque.springestoque_backend.domain.Funcionario;

public record FuncionarioBodyDTO(
        String nome,
        String cargo,
        String email,
        String telefone
) {

    public FuncionarioBodyDTO(Funcionario funcionario) {
        this(
                funcionario.getNome(),
                funcionario.getCargo().getNome(),
                funcionario.getEmail(),
                funcionario.getTelefone()
        );
    }

}
