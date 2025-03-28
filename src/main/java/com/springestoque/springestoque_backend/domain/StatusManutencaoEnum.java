package com.springestoque.springestoque_backend.domain;

import com.springestoque.springestoque_backend.exception.StatusManutencaoInvalidoException;
import com.springestoque.springestoque_backend.exception.TipoMovimentacaoNotFoundException;

public enum StatusManutencaoEnum {
    ANDAMENTO("ANDAMENTO"),
    CONCLUIDA("CONCLUIDA"),
    CANCELADA("CANCELADA");

    private String status;

    StatusManutencaoEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static StatusManutencaoEnum fromString(String text) {
        for (StatusManutencaoEnum statusManutencaoEnum : StatusManutencaoEnum.values()) {
            if (statusManutencaoEnum.status.equalsIgnoreCase(text)) {
                return statusManutencaoEnum;
            }
        }
        throw new StatusManutencaoInvalidoException(text);  // Exceção personalizada para status inválido
    }
}


