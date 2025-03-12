package com.springestoque.springestoque_backend.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springestoque.springestoque_backend.domain.Funcionario;
import com.springestoque.springestoque_backend.domain.Usuario;

public record UsuarioDTO(
        Long id,
        Long funcionario,
        @JsonProperty("nome_de_usuario")
        String nomeDoUsuario,
        String email,
        String senha
) {

    public UsuarioDTO(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getFuncionario().getId(),
                usuario.getNomeDeUsuario(),
                usuario.getEmail(),
                usuario.getSenha()
        );
    }

}
