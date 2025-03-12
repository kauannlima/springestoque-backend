package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Setor;
import com.springestoque.springestoque_backend.domain.dto.SetorBodyDTO;
import com.springestoque.springestoque_backend.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetorService {

    @Autowired
    private SetorRepository repository;

    public void cadastrarSetor(Setor setor){
        repository.save(setor);
    }

    public List<SetorBodyDTO> obterTodosOsSetores() {
        return repository.findAll().stream().map(SetorBodyDTO::new).toList();
    }
}
