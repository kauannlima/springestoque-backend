package com.springestoque.springestoque_backend.domain;

import com.springestoque.springestoque_backend.exception.TipoMovimentacaoNotFoundException;

public enum TipoMovimentacaoEnum {

    ENTRADA("Compra (entrada)"),
    SAIDA("Uso Interno (saída)");

    private String tipo;

    TipoMovimentacaoEnum(String tipo){
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static TipoMovimentacaoEnum fromString(String text) {
        for (TipoMovimentacaoEnum tipoMovimentacaoEnum : TipoMovimentacaoEnum.values()) {
            if (tipoMovimentacaoEnum.tipo.equalsIgnoreCase(text)) {
                return tipoMovimentacaoEnum;
            }
        }
        // Lançando a exceção personalizada
        throw new TipoMovimentacaoNotFoundException(text);
    }
}
