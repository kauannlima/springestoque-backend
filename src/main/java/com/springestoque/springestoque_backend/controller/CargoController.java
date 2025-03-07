package com.springestoque.springestoque_backend.controller;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.Setor;
import com.springestoque.springestoque_backend.domain.dto.CargoDTO;
import com.springestoque.springestoque_backend.domain.dto.SetorDTO;
import com.springestoque.springestoque_backend.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cargo")
public class CargoController {

    @Autowired
    private CargoService servico;

  @CrossOrigin(origins = "*", allowedHeaders = "*")
   @PostMapping
    public ResponseEntity cadastrarCargo(@RequestBody Cargo cargo){
             servico.cadastrarCargo(cargo);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<CargoDTO>> obterTodosOsCargos() {
        return ResponseEntity.ok().body(servico.obterTodosOsCargos());
    }

}
