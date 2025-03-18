package com.springestoque.springestoque_backend.controller;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.dto.CargoBodyDTO;
import com.springestoque.springestoque_backend.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cargo")
public class CargoController {

    @Autowired
    private CargoService servico;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<CargoBodyDTO>> obterTodosOsCargos() {
        return ResponseEntity.ok().body(servico.obterTodosOsCargos());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{nome}")
    public ResponseEntity<List<CargoBodyDTO>> obterCargosPorNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(servico.obterCargosPorNome(nome));
    }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
   @PostMapping
    public ResponseEntity cadastrarCargo(@RequestBody Cargo cargo){
             servico.cadastrarCargo(cargo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity editarCargo(@PathVariable Long id, @RequestBody Cargo cargo){
        servico.editarCargo(id, cargo);
        return ResponseEntity.status(HttpStatus.OK).build();
    }



}
