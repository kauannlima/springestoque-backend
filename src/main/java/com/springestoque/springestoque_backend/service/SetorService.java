package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.Setor;
import com.springestoque.springestoque_backend.domain.dto.CargoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.SetorBodyDTO;
import com.springestoque.springestoque_backend.exception.CargoNaoEncontradoException;
import com.springestoque.springestoque_backend.exception.SetorNaoEncontradoException;
import com.springestoque.springestoque_backend.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetorService {

    @Autowired
    private SetorRepository repository;

    public List<SetorBodyDTO> obterTodosOsSetores() {
        return repository.findAll().stream().map(SetorBodyDTO::new).toList();
    }

    public List<SetorBodyDTO> obterSetoresPorNome(String nome) {
        List<Setor> setores = repository.findAllByNomeContainingIgnoreCase(nome);

        if (setores.isEmpty()) {
            throw new SetorNaoEncontradoException(nome);  // Exceção customizada
        }

        return setores.stream()
                .map(SetorBodyDTO::new)  // Convertendo de Cargo para CargoBodyDTO
                .collect(Collectors.toList());  // Coletando em uma lista
    }

    public void cadastrarSetor(Setor setor){
        repository.save(setor);
    }

}
