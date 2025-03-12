package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Categoria;
import com.springestoque.springestoque_backend.domain.dto.CategoriaBodyDTO;
import com.springestoque.springestoque_backend.repository.CategoriaRepository;
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

    public List<CategoriaBodyDTO> obterTodasAsCategorias() {
        return repository.findAll().stream().map(CategoriaBodyDTO::new).toList();
    }
}
