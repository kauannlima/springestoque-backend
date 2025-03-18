package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.Produto;
import com.springestoque.springestoque_backend.domain.dto.CargoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.ProdutoBodyDTO;
import com.springestoque.springestoque_backend.exception.CargoNaoEncontradoException;
import com.springestoque.springestoque_backend.exception.EntidadeVinculadaException;
import com.springestoque.springestoque_backend.exception.ProdutoNaoEncontradoException;
import com.springestoque.springestoque_backend.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CargoService {

    @Autowired
    private CargoRepository repository;


    public List<CargoBodyDTO> obterTodosOsCargos() {
        return repository.findAll().stream().map(CargoBodyDTO::new).toList();
    }

    public List<CargoBodyDTO> obterCargosPorNome(String nome) {
        List<Cargo> cargos = repository.findAllByNomeContainingIgnoreCase(nome);

        if (cargos.isEmpty()) {
            throw new CargoNaoEncontradoException(nome);  // Exceção customizada
        }

        return cargos.stream()
                .map(CargoBodyDTO::new)  // Convertendo de Cargo para CargoBodyDTO
                .collect(Collectors.toList());  // Coletando em uma lista
    }

    public Cargo obterCargoPorId(Long id) {
        Cargo cargo = repository.findById(id).orElseThrow(() -> new CargoNaoEncontradoException(id));
        return cargo;
    }

    public void cadastrarCargo(Cargo cargo){
        repository.save(cargo);
    }

    public void editarCargo(Long id, Cargo cargo) {
        Cargo cargoBuscado = obterCargoPorId(id);

        if (cargo.getNome() != null && !cargo.getNome().isBlank()) {
            cargoBuscado.setNome(cargo.getNome());
        }

       repository.save(cargoBuscado);
    }

    public void excluirCargo(Long id) {
        Cargo cargo = obterCargoPorId(id);
        try {
            repository.delete(cargo);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeVinculadaException("Não é possível excluir o cargo, pois ele está vinculado a um ou mais funcionários.");
        }
    }


}
