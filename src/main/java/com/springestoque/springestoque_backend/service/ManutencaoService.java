package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.*;
import com.springestoque.springestoque_backend.domain.dto.ManutencaoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.ManutencaoDTO;
import com.springestoque.springestoque_backend.exception.*;
import com.springestoque.springestoque_backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ManutencaoService {

    @Autowired
    private ManutencaoRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;


    //Getter geral
    public List<ManutencaoBodyDTO> obterTodasManutencoes() {
        return repository.findAll().stream().map(ManutencaoBodyDTO::new).toList();
    }

    public List<ManutencaoBodyDTO> obterTodasManutencoesEmAndamento() {
        return repository.findByStatus(StatusManutencaoEnum.ANDAMENTO)
                .stream().map(ManutencaoBodyDTO::new).toList();
    }

    public List<ManutencaoBodyDTO> obterTodasManutencoesConcluidas() {
        return repository.findByStatus(StatusManutencaoEnum.CONCLUIDA)
                .stream().map(ManutencaoBodyDTO::new).toList();
    }

    public List<ManutencaoBodyDTO> obterTodasManutencoesCanceladas() {
        return repository.findByStatus(StatusManutencaoEnum.CANCELADA)
                .stream().map(ManutencaoBodyDTO::new).toList();
    }


    // Metodo apenas para uso interno, não será criado um endpoit para essa função
    public Manutencao obterManutencaoePorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new ManutencaoNaoEncontradaException(id));
    }


    public ManutencaoBodyDTO cadastrarManutencao(ManutencaoDTO manutencaoDTO) {
        Produto produto = produtoRepository.findById(manutencaoDTO.produto())
                .orElseThrow(() -> new ProdutoNaoEncontradoException(manutencaoDTO.produto()));

        if (produto.getQuantidadeEmEstoque() < manutencaoDTO.quantidadeManutencao()) {
            throw new EstoqueInsuficienteException(produto.getId(), produto.getQuantidadeEmEstoque(), manutencaoDTO.quantidadeManutencao());
        }

        // Cria a manutenção com o status "em andamento"
        Manutencao manutencaoRealizada = new Manutencao(
                null, produto, manutencaoDTO.quantidadeManutencao(), manutencaoDTO.dataInicio(),
                null, manutencaoDTO.descricao(), StatusManutencaoEnum.ANDAMENTO); // Status como ANDAMENTO

        // Atualiza a quantidade de estoque no momento do cadastro
        produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - manutencaoDTO.quantidadeManutencao());

        // Salva a manutenção e o produto
        manutencaoRealizada = repository.save(manutencaoRealizada);
        produtoRepository.save(produto);

        return new ManutencaoBodyDTO(
                manutencaoRealizada.getId(), produto.getNome(), manutencaoDTO.quantidadeManutencao(),
                manutencaoDTO.dataInicio(), null, // Data fim é nula no cadastro inicial
                manutencaoDTO.descricao(), manutencaoRealizada.getStatus().getStatus()
        );
    }
    public ManutencaoBodyDTO atualizarStatusManutencao(Long id, String novoStatus) {
        Manutencao manutencao = repository.findById(id)
                .orElseThrow(() -> new ManutencaoNaoEncontradaException(id));

        // Se o status já foi alterado para CONCLUIDA ou CANCELADA, não permite alterar novamente
        if (manutencao.getStatus().equals(StatusManutencaoEnum.CONCLUIDA) ||
                manutencao.getStatus().equals(StatusManutencaoEnum.CANCELADA)) {
            throw new StatusManutencaoJaAlteradoException(id, manutencao.getStatus().getStatus());
        }

        StatusManutencaoEnum novoStatusEnum = StatusManutencaoEnum.fromString(novoStatus);

        Produto produto = manutencao.getProduto();

        // Se a manutenção for CONCLUIDA ou CANCELADA, restabelece o estoque
        if (novoStatusEnum.equals(StatusManutencaoEnum.CONCLUIDA)) {
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + manutencao.getQuantidadeManutencao());
            manutencao.setDataFim(LocalDate.now());  // Atualiza a data de fim
        }else {
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + manutencao.getQuantidadeManutencao());
        }

        // Atualiza o status e salva
        manutencao.setStatus(novoStatusEnum);
        repository.save(manutencao);
        produtoRepository.save(produto);

        // Retorna o DTO atualizado com as informações da manutenção
        return new ManutencaoBodyDTO(
                manutencao.getId(), produto.getNome(), manutencao.getQuantidadeManutencao(),
                manutencao.getDataInicio(), manutencao.getDataFim(),
                manutencao.getDescricao(), manutencao.getStatus().getStatus()
        );
    }


}
