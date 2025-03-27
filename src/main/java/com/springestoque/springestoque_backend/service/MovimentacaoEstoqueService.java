package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.*;
import com.springestoque.springestoque_backend.domain.dto.MovimentacaoEstoqueBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.MovimentacaoEstoqueDTO;
import com.springestoque.springestoque_backend.domain.dto.ProdutoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.ProdutoDTO;
import com.springestoque.springestoque_backend.exception.*;
import com.springestoque.springestoque_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //Getter geral
    public List<MovimentacaoEstoqueBodyDTO> obterTodasMovimentacoes() {
        return repository.findAll().stream().map(MovimentacaoEstoqueBodyDTO::new).toList();
    }

    //Getter apenas para movimentações de entarda
    public List<MovimentacaoEstoqueBodyDTO> obterTodasMovimentacoesDeEntrada() {
        return repository.findAll().stream().filter(m -> m.getTipoDeMovimentacao().equals(TipoMovimentacaoEnum.ENTRADA)).map(MovimentacaoEstoqueBodyDTO::new).toList();
    }

    //Getter apenas para movimentações de saida
    public List<MovimentacaoEstoqueBodyDTO> obterTodasMovimentacoesDeSaida() {
        return repository.findAll().stream().filter(m -> m.getTipoDeMovimentacao().equals(TipoMovimentacaoEnum.SAIDA)).map(MovimentacaoEstoqueBodyDTO::new).toList();
    }

    // Metodo apenas para uso interno, não será criado um endpoit para essa função
    public MovimentacaoEstoque obterMovimentacaoEstoquePorId(Long id) {

        MovimentacaoEstoque movimentacaoEstoque = repository.findById(id).orElseThrow(() -> new MovimentacaoEstoqueNaoEncontradaException(id));
        return movimentacaoEstoque;
    }


    public MovimentacaoEstoqueBodyDTO realizarMovimentacao(MovimentacaoEstoqueDTO movimentacaoDTO) {
        // Buscar o produto, funcionário e setor com base nos IDs fornecidos no movimentacaoDTO
        Produto produto = produtoRepository.findById(movimentacaoDTO.produto())
                .orElseThrow(() -> new ProdutoNaoEncontradoException(movimentacaoDTO.produto()));

        Funcionario funcionario = funcionarioRepository.findById(movimentacaoDTO.funcionario())
                .orElseThrow(() -> new FuncionarioNaoEncontradoException(movimentacaoDTO.funcionario()));

        //não é informar o Setor para "entrada" de estoque
        Setor setor = null;
        if (!movimentacaoDTO.tipoDeMovimentacao().equals(TipoMovimentacaoEnum.ENTRADA)) {
            setor = setorRepository.findById(movimentacaoDTO.setorDestino())
                    .orElseThrow(() -> new SetorNaoEncontradoException(movimentacaoDTO.setorDestino()));
        }

        // Verifica se há estoque suficiente para o tipo "saída"
        if (movimentacaoDTO.tipoDeMovimentacao().equals(TipoMovimentacaoEnum.SAIDA)) {
            if (produto.getQuantidadeEmEstoque() < movimentacaoDTO.quantidadeMovimentada()) {
                throw new EstoqueInsuficienteException(produto.getId(), produto.getQuantidadeEmEstoque(), movimentacaoDTO.quantidadeMovimentada());
            }
        }

        // Cria a movimentação de estoque
        MovimentacaoEstoque movimentacaoRealizada = new MovimentacaoEstoque(
                null, produto, movimentacaoDTO.quantidadeMovimentada(),
                movimentacaoDTO.tipoDeMovimentacao(), movimentacaoDTO.dataDaMovimentacao(),
                funcionario, setor);

        // Atualiza a quantidade em estoque
        if (movimentacaoDTO.tipoDeMovimentacao().equals(TipoMovimentacaoEnum.ENTRADA)) {
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + movimentacaoDTO.quantidadeMovimentada());
        } else {
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - movimentacaoDTO.quantidadeMovimentada());
        }

        // Salva o produto atualizado
        produtoRepository.save(produto);

        // Salva a movimentação realizada no banco
        movimentacaoRealizada = repository.save(movimentacaoRealizada);

        // Retorna o DTO com as informações da movimentação
        return new MovimentacaoEstoqueBodyDTO(
                movimentacaoRealizada.getId(),
                produto.getNome(),
                movimentacaoDTO.quantidadeMovimentada(),
                movimentacaoDTO.tipoDeMovimentacao().getTipo(),
                movimentacaoDTO.dataDaMovimentacao(),
                funcionario.getNome(),
                setor != null ? setor.getNome() : null
        );
    }

    public void cancelarMovimentacao(Long id) {
        MovimentacaoEstoque movimentacaoBuscada = obterMovimentacaoEstoquePorId(id);

        Produto produto = produtoRepository.findById(movimentacaoBuscada.getProduto().getId())
                .orElseThrow(() -> new ProdutoNaoEncontradoException(movimentacaoBuscada.getProduto().getId()));

        // Se a movimentação for de ENTRADA, verifica se há estoque suficiente para excluir
        if (movimentacaoBuscada.getTipoDeMovimentacao().equals(TipoMovimentacaoEnum.ENTRADA) &&
                produto.getQuantidadeEmEstoque() < movimentacaoBuscada.getQuantidadeMovimentada()) {
            throw new EstoqueInsuficienteException( produto.getQuantidadeEmEstoque(), movimentacaoBuscada.getQuantidadeMovimentada());
        }

        // Ajusta a quantidade do estoque ao excluir a movimentação
        if (movimentacaoBuscada.getTipoDeMovimentacao().equals(TipoMovimentacaoEnum.ENTRADA)) {
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - movimentacaoBuscada.getQuantidadeMovimentada());
        } else {
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + movimentacaoBuscada.getQuantidadeMovimentada());
        }

        // Salva a atualização e remove a movimentação
        produtoRepository.save(produto);
        repository.delete(movimentacaoBuscada);
    }



}
