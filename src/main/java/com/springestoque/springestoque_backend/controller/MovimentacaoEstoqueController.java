package com.springestoque.springestoque_backend.controller;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.MovimentacaoEstoque;
import com.springestoque.springestoque_backend.domain.dto.MovimentacaoEstoqueBodyDTO;
import com.springestoque.springestoque_backend.service.CategoriaService;
import com.springestoque.springestoque_backend.service.MovimentacaoEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movimentacao")
public class MovimentacaoEstoqueController {

    @Autowired
    private MovimentacaoEstoqueService servico;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/{id}")
    public ResponseEntity realizarMovimentacao(@PathVariable Long id, @RequestBody MovimentacaoEstoque movimentacao){
        return ResponseEntity.status(HttpStatus.OK).body(servico.realizarMovimentacao(id, movimentacao));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity cancelarMovimentacao(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
