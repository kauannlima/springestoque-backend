package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.*;
import com.springestoque.springestoque_backend.domain.dto.MovimentacaoEstoqueBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.ProdutoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.ProdutoDTO;
import com.springestoque.springestoque_backend.exception.*;
import com.springestoque.springestoque_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimentacaoEstoqueService {

    @Autowired
    private MovimentacaoEstoqueRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private SetorRepository setorRepository;


    public MovimentacaoEstoqueBodyDTO realizarMovimentacao(Long id, MovimentacaoEstoque movimentacao) {

        Produto produto = produtoRepository.findById(movimentacao.getProduto().getId())
                .orElseThrow(() -> new ProdutoNaoEncontradoException(movimentacao.getProduto().getId()));

        Funcionario funcionario = funcionarioRepository.findById(movimentacao.getFuncionario().getId())
                .orElseThrow(() -> new FuncionarioNaoEncontradoException(movimentacao.getFuncionario().getId()));

        Setor setor = setorRepository.findById(movimentacao.getSetor().getId())
                .orElseThrow(() -> new SetorNaoEncontradoException(movimentacao.getSetor().getId()));


        MovimentacaoEstoque MovimentacaoRealizada = new MovimentacaoEstoque(null, produto, movimentacao.getQuantidadeMovimentada(), movimentacao.getTipoDeMovimentacao(), movimentacao.getDataDaMovimentacao(), funcionario, setor);

        MovimentacaoRealizada = repository.save(MovimentacaoRealizada);

        return new MovimentacaoEstoqueBodyDTO(MovimentacaoRealizada.getId(), produto.getNome(), movimentacao.getQuantidadeMovimentada(),movimentacao.getTipoDeMovimentacao().getTipo(), movimentacao.getDataDaMovimentacao(), funcionario.getNome(), setor.getNome() );

    }
}
