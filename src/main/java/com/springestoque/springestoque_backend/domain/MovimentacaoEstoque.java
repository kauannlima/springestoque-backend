package com.springestoque.springestoque_backend.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "movimentacao_estoque")
public class MovimentacaoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "quantidade_movimentada", nullable = false)
    private Integer quantidadeMovimentada;

    @Enumerated(EnumType.STRING) // Salva o nome da constante
    @Column(name = "tipo_de_movimentacao", nullable = false)
    private TipoMovimentacaoEnum tipoDeMovimentacao;

    @Column(name = "data_da_movimentacao", nullable = false)
    private LocalDate dataDaMovimentacao;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "setor_destino_id ", nullable = false)
    private Setor setor;

    public MovimentacaoEstoque(Long id, Produto produto, Integer quantidadeMovimentada, TipoMovimentacaoEnum tipo, LocalDate dataMovimentacao, Funcionario funcionario, Setor setor) {
        this.id = id;
        this.produto = produto;
        this.quantidadeMovimentada = quantidadeMovimentada;
        this.tipoDeMovimentacao = tipo;
        this.dataDaMovimentacao = dataMovimentacao;
        this.funcionario = funcionario;
        this.setor = setor;
    }

    public MovimentacaoEstoque() {
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

    public Integer getQuantidadeMovimentada() {
        return quantidadeMovimentada;
    }

    public void setQuantidadeMovimentada(Integer quantidadeMovimentada) {
        this.quantidadeMovimentada = quantidadeMovimentada;
    }

    public TipoMovimentacaoEnum getTipoDeMovimentacao() {
        return tipoDeMovimentacao;
    }

    public void setTipoDeMovimentacao(TipoMovimentacaoEnum tipoDeMovimentacao) {
        this.tipoDeMovimentacao = tipoDeMovimentacao;
    }

    public LocalDate getDataDaMovimentacao() {
        return dataDaMovimentacao;
    }

    public void setDataDaMovimentacao(LocalDate dataDaMovimentacao) {
        this.dataDaMovimentacao = dataDaMovimentacao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }
}
