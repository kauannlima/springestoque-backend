package com.springestoque.springestoque_backend.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Manutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "quantidade_manutencao", nullable = false)
    private Integer quantidadeManutencao;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    //A data fim será informada ao concluir a manutenção
    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING) // Salva o nome da constante
    @Column(name = "status_da_manutencao  ", nullable = false)
    private StatusManutencaoEnum status;

    public Manutencao() {
    }

    public Manutencao(Long id, Produto produto, Integer quantidadeManutencao, LocalDate dataInicio, LocalDate dataFim, String descricao, StatusManutencaoEnum status) {
        this.id = id;
        this.produto = produto;
        this.quantidadeManutencao = quantidadeManutencao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.descricao = descricao;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidadeManutencao() {
        return quantidadeManutencao;
    }

    public void setQuantidadeManutencao(Integer quantidadeManutencao) {
        this.quantidadeManutencao = quantidadeManutencao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusManutencaoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusManutencaoEnum tipo) {
        this.status = tipo;
    }
}
