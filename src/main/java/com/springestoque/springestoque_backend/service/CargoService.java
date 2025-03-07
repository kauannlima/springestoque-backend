package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.Setor;
import com.springestoque.springestoque_backend.domain.dto.CargoDTO;
import com.springestoque.springestoque_backend.domain.dto.SetorDTO;
import com.springestoque.springestoque_backend.repository.CargoRepository;
import com.springestoque.springestoque_backend.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {

    @Autowired
    private CargoRepository repository;

    public void cadastrarCargo(Cargo cargo){
        repository.save(cargo);
    }

    public List<CargoDTO> obterTodosOsCargos() {
        return repository.findAll().stream().map(CargoDTO::new).toList();
    }
}
