package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.Categoria;
import com.springestoque.springestoque_backend.domain.dto.CargoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.CategoriaBodyDTO;
import com.springestoque.springestoque_backend.exception.CargoNaoEncontradoException;
import com.springestoque.springestoque_backend.exception.CategoriaNaoEncontradaException;
import com.springestoque.springestoque_backend.exception.EntidadeVinculadaException;
import com.springestoque.springestoque_backend.exception.ProdutoNaoEncontradoException;
import com.springestoque.springestoque_backend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public List<CategoriaBodyDTO> obterTodasAsCategorias() {
        return repository.findAll().stream().map(CategoriaBodyDTO::new).toList();
    }

    public List<CategoriaBodyDTO> obterCategoriasPorNome(String nome) {
        List<Categoria> categorias = repository.findAllByNomeContainingIgnoreCase(nome);

        if (categorias.isEmpty()) {
            throw new CategoriaNaoEncontradaException(nome);  // Exceção customizada
        }

        return categorias.stream()
                .map(CategoriaBodyDTO::new)  // Convertendo de Cargo para CargoBodyDTO
                .collect(Collectors.toList());  // Coletando em uma lista
    }

    public Categoria obterCategoriaPorId(Long id) {
        Categoria categoria = repository.findById(id).orElseThrow(() -> new CargoNaoEncontradoException(id));
        return categoria;
    }

    public void cadastrarCategoria(Categoria categoria){
        repository.save(categoria);
    }

    public void editarCategoria(Long id, Categoria categoria) {
        Categoria categoriaBuscada = obterCategoriaPorId(id);

        if (categoria.getNome() != null && !categoria.getNome().isBlank()) {
            categoriaBuscada.setNome(categoria.getNome());
        }
        if (categoria.getDescricao() != null && !categoria.getDescricao().isBlank()) {
            categoriaBuscada.setDescricao(categoria.getDescricao());
        }

        repository.save(categoriaBuscada);
    }

    public void excluirCategoria(Long id) {
        Categoria categoria = obterCategoriaPorId(id);
        try {
            repository.delete(categoria);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeVinculadaException("Não é possível excluir a categoria, pois ela está vinculada a um ou mais produtos.");
        }
    }



}
