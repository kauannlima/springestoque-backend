package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.Categoria;
import com.springestoque.springestoque_backend.domain.Fornecedor;
import com.springestoque.springestoque_backend.domain.Produto;
import com.springestoque.springestoque_backend.domain.dto.CargoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.ProdutoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.ProdutoDTO;
import com.springestoque.springestoque_backend.exception.*;
import com.springestoque.springestoque_backend.repository.CategoriaRepository;
import com.springestoque.springestoque_backend.repository.FornecedorRepository;
import com.springestoque.springestoque_backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<ProdutoBodyDTO> obterTodosOsProdutos() {
        return repository.findAll().stream().map(ProdutoBodyDTO::new).toList();
    }

    //Metodo sera usado para facitar a busca no front atraves do nome
    public List<ProdutoBodyDTO> obterProdutosPorNome(String nome) {
        Optional<Produto> produtos = repository.findAllByNomeContainingIgnoreCase(nome);

        if (produtos.isEmpty()) {
            throw new ProdutoNaoEncontradoException(nome);  // Exceção customizada
        }

        return produtos.stream()
                .map(ProdutoBodyDTO::new)
                .collect(Collectors.toList());
    }

    public Produto obterProdutoPorId(Long id) {
        Produto produto = repository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException(id));
        return produto;
    }

    public ProdutoBodyDTO cadastrarProduto(ProdutoDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.categoria())
                .orElseThrow(() -> new CategoriaNaoEncontradaException(dto.categoria()));

        Fornecedor fornecedor = fornecedorRepository.findById(dto.fornecedor())
                .orElseThrow(() -> new FornecedorNaoEncontradoException(dto.fornecedor()));

        Produto produto = new Produto(null, dto.nome(), categoria, dto.descricao(), 0, fornecedor);

        produto = repository.save(produto);

        return new ProdutoBodyDTO(produto.getId(),produto.getNome(), produto.getCategoria().getNome(),
                produto.getDescricao(), produto.getQuantidadeEmEstoque(),
                produto.getFornecedor().getNome());
    }

    public void editarProduto(Long id, Produto produto) {
        Produto produtoBuscado = obterProdutoPorId(id);

        if (produto.getNome() != null && !produto.getNome().isBlank()) {
            produtoBuscado.setNome(produto.getNome());  // Corrigido aqui para usar o valor de produto
        }
        if (produto.getDescricao() != null && !produto.getDescricao().isBlank()) {
            produtoBuscado.setDescricao(produto.getDescricao());  // Corrigido aqui também
        }

        // Não alterando a quantidade em estoque
        repository.save(produtoBuscado);
    }


    public void excluirProduto(Long id) {
        Produto produto = obterProdutoPorId(id);
        if (produto.getCategoria() != null) {
            produto.setCategoria(null);  // Desvincula a categoria
        }
        if (produto.getFornecedor() != null) {
            produto.setFornecedor(null);  // Desvincula o fornecedor
        }
        try {
            // Agora que o produto está desvinculado, podemos excluir sem problema
            repository.delete(produto);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeVinculadaException("Erro ao tentar excluir o produto.");
        }
    }




}
