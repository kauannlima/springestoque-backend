package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Categoria;
import com.springestoque.springestoque_backend.domain.Fornecedor;
import com.springestoque.springestoque_backend.domain.dto.FornecedorDTO;
import com.springestoque.springestoque_backend.domain.dto.ProdutoDTO;
import com.springestoque.springestoque_backend.repository.CategoriaRepository;
import com.springestoque.springestoque_backend.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;

    public void cadastrarFornecedor(Fornecedor fornecedor){
        repository.save(fornecedor);
    }

    public List<FornecedorDTO> obterTodosOsFornecedores() {

        return repository.findAll().stream().map(FornecedorDTO::new).toList();
    }
}
