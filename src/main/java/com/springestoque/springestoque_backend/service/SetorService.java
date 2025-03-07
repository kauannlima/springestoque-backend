package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Categoria;
import com.springestoque.springestoque_backend.domain.Setor;
import com.springestoque.springestoque_backend.domain.dto.CategoriaDTO;
import com.springestoque.springestoque_backend.domain.dto.SetorDTO;
import com.springestoque.springestoque_backend.repository.CategoriaRepository;
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

    public List<SetorDTO> obterTodosOsSetores() {
        return repository.findAll().stream().map(SetorDTO::new).toList();
    }
}
