package com.springestoque.springestoque_backend.domain.dto;

import com.springestoque.springestoque_backend.domain.Funcionario;

public record FuncionarioBodyDTO(
        Long id,
        String nome,
        String cargo,
        String email,
        String telefone
) {

    public FuncionarioBodyDTO(Funcionario funcionario) {
        this(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCargo().getNome(),
                funcionario.getEmail(),
                funcionario.getTelefone()
        );
    }

}
