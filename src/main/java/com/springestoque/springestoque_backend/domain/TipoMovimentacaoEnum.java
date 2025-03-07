package com.springestoque.springestoque_backend.domain;

public enum TipoMovimentacaoEnum {

    ENTRADA("Compra (entrada)"),
    SAIDA("Uso Interno (saída)");

    private String tipo;

    TipoMovimentacaoEnum(String tipo){
        this.tipo = tipo;
    }

    public static TipoMovimentacaoEnum fromString(String text) {
        for (TipoMovimentacaoEnum tipoMovimentacaoEnum : TipoMovimentacaoEnum.values()) {
            if (tipoMovimentacaoEnum.tipo.equalsIgnoreCase(text)) {
                return tipoMovimentacaoEnum;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo encontrada para a string fornecida: " + text);
    }
}
