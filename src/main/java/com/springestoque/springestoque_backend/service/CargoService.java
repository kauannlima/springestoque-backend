package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.dto.CargoBodyDTO;
import com.springestoque.springestoque_backend.repository.CargoRepository;
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

    public List<CargoBodyDTO> obterTodosOsCargos() {
        return repository.findAll().stream().map(CargoBodyDTO::new).toList();
    }
}
