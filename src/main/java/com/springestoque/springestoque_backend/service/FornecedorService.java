package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.Categoria;
import com.springestoque.springestoque_backend.domain.Fornecedor;
import com.springestoque.springestoque_backend.domain.dto.CargoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.CategoriaBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.FornecedorBodyDTO;
import com.springestoque.springestoque_backend.exception.CargoNaoEncontradoException;
import com.springestoque.springestoque_backend.exception.FornecedorNaoEncontradoException;
import com.springestoque.springestoque_backend.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository repository;



    public List<FornecedorBodyDTO> obterTodosOsFornecedores() {

        return repository.findAll().stream().map(FornecedorBodyDTO::new).toList();
    }

    public List<FornecedorBodyDTO> obterFornecedoresPorNome(String nome) {
        List<Fornecedor> fornecedores = repository.findAllByNomeContainingIgnoreCase(nome);

        if (fornecedores.isEmpty()) {
            throw new FornecedorNaoEncontradoException(nome);  // Exceção customizada
        }

        return fornecedores.stream()
                .map(FornecedorBodyDTO::new)  // Convertendo de Cargo para CargoBodyDTO
                .collect(Collectors.toList());  // Coletando em uma lista
    }

    public FornecedorBodyDTO obterFornecedorPorId(Long id) {
        Fornecedor fornecedor = repository.findById(id).orElseThrow(() -> new FornecedorNaoEncontradoException(id));
        return new FornecedorBodyDTO(fornecedor);
    }

    public void cadastrarFornecedor(Fornecedor fornecedor){
        repository.save(fornecedor);
    }
}
