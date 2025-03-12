package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Fornecedor;
import com.springestoque.springestoque_backend.domain.dto.FornecedorBodyDTO;
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

    public List<FornecedorBodyDTO> obterTodosOsFornecedores() {

        return repository.findAll().stream().map(FornecedorBodyDTO::new).toList();
    }
}
