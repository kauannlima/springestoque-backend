package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.*;
import com.springestoque.springestoque_backend.domain.dto.CargoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.FornecedorBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.FuncionarioBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.FuncionarioDTO;
import com.springestoque.springestoque_backend.exception.CargoNaoEncontradoException;
import com.springestoque.springestoque_backend.exception.FornecedorNaoEncontradoException;
import com.springestoque.springestoque_backend.exception.FuncionarioNaoEncontradoException;
import com.springestoque.springestoque_backend.repository.CargoRepository;
import com.springestoque.springestoque_backend.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private CargoRepository cargoRepository;

    public List<FuncionarioBodyDTO> obterTodosOsFuncionarios() {

        return repository.findAll().stream().map(FuncionarioBodyDTO::new).toList();
    }

    public List<FuncionarioBodyDTO> obterFuncionariosPorNome(String nome) {
        List<Funcionario> funcionarios = repository.findAllByNomeContainingIgnoreCase(nome);

        if (funcionarios.isEmpty()) {
            throw new FuncionarioNaoEncontradoException(nome);  // Exceção customizada
        }

        return funcionarios.stream()
                .map(FuncionarioBodyDTO::new)  // Convertendo de Cargo para CargoBodyDTO
                .collect(Collectors.toList());  // Coletando em uma lista
    }

    public FuncionarioBodyDTO obterFuncionarioPorId(Long id) {
        Funcionario funcionario = repository.findById(id).orElseThrow(() -> new FuncionarioNaoEncontradoException(id));
        return new FuncionarioBodyDTO(funcionario);
    }


    public FuncionarioBodyDTO cadastrarFuncionario(FuncionarioDTO dto){

        Cargo cargo = cargoRepository.findById(dto.cargo())
                .orElseThrow(() -> new CargoNaoEncontradoException(dto.cargo()));

        Funcionario funcionario = new Funcionario(null, dto.nome(), cargo, dto.email(), dto.telefone());

        funcionario = repository.save(funcionario);

        return new FuncionarioBodyDTO(funcionario.getNome(), funcionario.getCargo().getNome(),
                funcionario.getEmail(), funcionario.getTelefone());

    }
}
