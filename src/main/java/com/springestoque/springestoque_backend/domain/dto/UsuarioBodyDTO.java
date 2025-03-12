package com.springestoque.springestoque_backend.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springestoque.springestoque_backend.domain.Usuario;

public record UsuarioBodyDTO(
        String funcionario,
        @JsonProperty("nome_de_usuario")
        String nomeDoUsuario
) {

    public UsuarioBodyDTO(Usuario usuario) {
        this(
                usuario.getFuncionario().getNome(),
                usuario.getNomeDeUsuario()
        );
    }

}
