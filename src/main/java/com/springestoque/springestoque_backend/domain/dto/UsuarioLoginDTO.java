package com.springestoque.springestoque_backend.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springestoque.springestoque_backend.domain.Usuario;

public record UsuarioLoginDTO(
        @JsonProperty("nome_de_usuario")
        String nomeDoUsuario,
        String senha
) {

    public UsuarioLoginDTO(Usuario usuario) {
        this(
                usuario.getNomeDeUsuario(),
                usuario.getSenha()
        );
    }

}
