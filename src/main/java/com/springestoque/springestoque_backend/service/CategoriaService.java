package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Categoria;
import com.springestoque.springestoque_backend.domain.Produto;
import com.springestoque.springestoque_backend.domain.dto.CategoriaDTO;
import com.springestoque.springestoque_backend.domain.dto.FornecedorDTO;
import com.springestoque.springestoque_backend.domain.dto.ProdutoDTO;
import com.springestoque.springestoque_backend.repository.CategoriaRepository;
import com.springestoque.springestoque_backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public void cadastrarCategoria(Categoria categoria){
        repository.save(categoria);
    }

    public List<CategoriaDTO> obterTodasAsCategorias() {
        return repository.findAll().stream().map(CategoriaDTO::new).toList();
    }
}
