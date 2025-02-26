package com.springestoque.springestoque_backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "movimentacao_estoque")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "quantidade_movimentada", nullable = false)
    private Integer quantidadeMovimentada;

    @Column(name = "tipo_de_movimentacao", nullable = false)
    private TipoMovimentacaoEnum tipo;

    @Column(name = "data_da_movimentacao", nullable = false)
    private LocalDate dataMovimentacao;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "setor_destino_id ", nullable = false)
    private Setor setor;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;


}
