package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.Categoria;
import com.springestoque.springestoque_backend.domain.Fornecedor;
import com.springestoque.springestoque_backend.domain.Produto;
import com.springestoque.springestoque_backend.domain.dto.CargoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.ProdutoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.ProdutoDTO;
import com.springestoque.springestoque_backend.exception.CargoNaoEncontradoException;
import com.springestoque.springestoque_backend.exception.CategoriaNaoEncontradaException;
import com.springestoque.springestoque_backend.exception.FornecedorNaoEncontradoException;
import com.springestoque.springestoque_backend.exception.ProdutoNaoEncontradoException;
import com.springestoque.springestoque_backend.repository.CategoriaRepository;
import com.springestoque.springestoque_backend.repository.FornecedorRepository;
import com.springestoque.springestoque_backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<ProdutoBodyDTO> obterProdutosPorNome(String nome) {
        Optional<Produto> produtos = repository.findAllByNomeContainingIgnoreCase(nome);

        if (produtos.isEmpty()) {
            throw new ProdutoNaoEncontradoException(nome);  // Exceção customizada
        }

        return produtos.stream()
                .map(ProdutoBodyDTO::new)  // Convertendo de Cargo para CargoBodyDTO
                .collect(Collectors.toList());  // Coletando em uma lista
    }

    public ProdutoBodyDTO obterProdutoPorId(Long id) {
        Produto produto = repository.findById(id).orElseThrow(() -> new ProdutoNaoEncontradoException(id));
        return new ProdutoBodyDTO(produto);
    }

    public ProdutoBodyDTO cadastrarProduto(ProdutoDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.categoria())
                .orElseThrow(() -> new CategoriaNaoEncontradaException(dto.categoria()));

        Fornecedor fornecedor = fornecedorRepository.findById(dto.fornecedor())
                .orElseThrow(() -> new FornecedorNaoEncontradoException(dto.fornecedor()));

        Produto produto = new Produto(null, dto.nome(), categoria, dto.descricao(), dto.quantidadeEmEstoque(), fornecedor);

        produto = repository.save(produto);

        return new ProdutoBodyDTO(produto.getNome(), produto.getCategoria().getNome(),
                produto.getDescricao(), produto.getQuantidadeEmEstoque(),
                produto.getFornecedor().getNome());
    }


}
