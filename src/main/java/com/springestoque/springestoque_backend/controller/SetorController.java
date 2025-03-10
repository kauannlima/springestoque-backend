package com.springestoque.springestoque_backend.controller;

import com.springestoque.springestoque_backend.domain.Setor;
import com.springestoque.springestoque_backend.domain.dto.SetorDTO;
import com.springestoque.springestoque_backend.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("setor")
public class SetorController {

    @Autowired
    private SetorService servico;

  @CrossOrigin(origins = "*", allowedHeaders = "*")
   @PostMapping
    public ResponseEntity cadastrarSetor(@RequestBody Setor setor){
             servico.cadastrarSetor(setor);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<SetorDTO>> obterTodosOsSetores() {
        return ResponseEntity.ok().body(servico.obterTodosOsSetores());
    }

}
