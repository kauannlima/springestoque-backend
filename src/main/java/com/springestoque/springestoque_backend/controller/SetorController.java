package com.springestoque.springestoque_backend.controller;

import com.springestoque.springestoque_backend.domain.Setor;
import com.springestoque.springestoque_backend.domain.dto.ProdutoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.SetorBodyDTO;
import com.springestoque.springestoque_backend.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("setor")
public class SetorController {

    @Autowired
    private SetorService servico;



    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<SetorBodyDTO>> obterTodosOsSetores() {
        return ResponseEntity.ok().body(servico.obterTodosOsSetores());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{nome}")
    public ResponseEntity<List<SetorBodyDTO>> obterSetoresPorNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(servico.obterSetoresPorNome(nome));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity cadastrarSetor(@RequestBody Setor setor){
        servico.cadastrarSetor(setor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity editarSetor(@PathVariable Long id, @RequestBody Setor setor) {
        servico.editarSetor(id, setor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity excluirSetor(@PathVariable Long id) {
        servico.excluirSetor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }





}
