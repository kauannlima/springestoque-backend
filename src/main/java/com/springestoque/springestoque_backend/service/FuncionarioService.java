package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.Funcionario;
import com.springestoque.springestoque_backend.domain.dto.FuncionarioBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.FuncionarioDTO;
import com.springestoque.springestoque_backend.exception.CargoNaoEncontradoException;
import com.springestoque.springestoque_backend.exception.EntidadeVinculadaException;
import com.springestoque.springestoque_backend.exception.FuncionarioNaoEncontradoException;
import com.springestoque.springestoque_backend.repository.CargoRepository;
import com.springestoque.springestoque_backend.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    //Metodo sera usado para facitar a busca no front atraves do nome
    public List<FuncionarioBodyDTO> obterFuncionariosPorNome(String nome) {
        List<Funcionario> funcionarios = repository.findAllByNomeContainingIgnoreCase(nome);

        if (funcionarios.isEmpty()) {
            throw new FuncionarioNaoEncontradoException(nome);  // Exceção customizada
        }

        return funcionarios.stream()
                .map(FuncionarioBodyDTO::new)  // Convertendo de Cargo para CargoBodyDTO
                .collect(Collectors.toList());  // Coletando em uma lista
    }

    // Metodo apenas para uso interno, não será criado um endpoit para essa função
    public Funcionario obterFuncionarioPorId(Long id) {
        Funcionario funcionario = repository.findById(id).orElseThrow(() -> new FuncionarioNaoEncontradoException(id));
        return funcionario;
    }


    public FuncionarioBodyDTO cadastrarFuncionario(FuncionarioDTO dto){

        Cargo cargo = cargoRepository.findById(dto.cargo())
                .orElseThrow(() -> new CargoNaoEncontradoException(dto.cargo()));

        Funcionario funcionario = new Funcionario(null, dto.nome(), cargo, dto.telefone());

        funcionario = repository.save(funcionario);

        return new FuncionarioBodyDTO(funcionario.getId(), funcionario.getNome(), funcionario.getCargo().getNome(), funcionario.getTelefone());

    }

    public void editarFuncionario(Long id, Funcionario funcionario) {
        Funcionario funcionarioBuscado = obterFuncionarioPorId(id);

        if (funcionario.getNome() != null && !funcionario.getNome().isBlank()) {
            funcionarioBuscado.setNome(funcionario.getNome());
        }
        if (funcionario.getTelefone() != null && !funcionario.getTelefone().isBlank()) {
            funcionarioBuscado.setTelefone(funcionario.getTelefone());
        }

        repository.save(funcionarioBuscado);
    }

    public void excluirFuncionario(Long id) {
        Funcionario funcionario = obterFuncionarioPorId(id);
        try {
            repository.delete(funcionario);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeVinculadaException("Não é possível excluir o funcionário, pois ele está vinculado a um usuário.");
        }
    }


}
