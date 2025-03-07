package com.springestoque.springestoque_backend.domain;

public enum StatusManutencaoEnum {

    ANDAMENTO("Em andamento"),
    CONCLUIDA("Concluída");

    private String tipo;

    StatusManutencaoEnum(String tipo){
        this.tipo = tipo;
    }

    public static StatusManutencaoEnum fromString(String text) {
        for (StatusManutencaoEnum statusManutencaoEnum : StatusManutencaoEnum.values()) {
            if (statusManutencaoEnum.tipo.equalsIgnoreCase(text)) {
                return statusManutencaoEnum;
            }
        }
        throw new IllegalArgumentException("Nenhum status encontrado para a string fornecida: " + text);
    }
}

