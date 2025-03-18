package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.Categoria;
import com.springestoque.springestoque_backend.domain.Fornecedor;
import com.springestoque.springestoque_backend.domain.dto.CargoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.CategoriaBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.FornecedorBodyDTO;
import com.springestoque.springestoque_backend.exception.CargoNaoEncontradoException;
import com.springestoque.springestoque_backend.exception.EntidadeVinculadaException;
import com.springestoque.springestoque_backend.exception.FornecedorNaoEncontradoException;
import com.springestoque.springestoque_backend.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    public  Fornecedor obterFornecedorPorId(Long id) {
        Fornecedor fornecedor = repository.findById(id).orElseThrow(() -> new FornecedorNaoEncontradoException(id));
        return fornecedor;
    }

    public void cadastrarFornecedor(Fornecedor fornecedor){
        repository.save(fornecedor);
    }

    public void editarFornecedor(Long id, Fornecedor fornecedor) {
        Fornecedor fornecedorBuscado = obterFornecedorPorId(id);

        if (fornecedor.getNome() != null && !fornecedor.getNome().isBlank()) {
            fornecedorBuscado.setNome(fornecedor.getNome());
        }
        if (fornecedor.getEmail() != null && !fornecedor.getEmail().isBlank()) {
            fornecedorBuscado.setEmail(fornecedor.getEmail());
        }

        repository.save(fornecedorBuscado);
    }

    public void excluirFornecedor(Long id) {
        Fornecedor fornecedor = obterFornecedorPorId(id);
        try {
            repository.delete(fornecedor);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeVinculadaException("Não é possível excluir o fornecedor, pois ele está vinculado a um ou mais produtos.");
        }
    }


}
