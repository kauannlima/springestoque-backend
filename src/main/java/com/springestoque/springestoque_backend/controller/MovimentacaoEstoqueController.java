package com.springestoque.springestoque_backend.controller;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.MovimentacaoEstoque;
import com.springestoque.springestoque_backend.domain.dto.MovimentacaoEstoqueBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.MovimentacaoEstoqueDTO;
import com.springestoque.springestoque_backend.domain.dto.ProdutoBodyDTO;
import com.springestoque.springestoque_backend.service.CategoriaService;
import com.springestoque.springestoque_backend.service.MovimentacaoEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movimentacao")
public class MovimentacaoEstoqueController {

    @Autowired
    private MovimentacaoEstoqueService servico;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping()
    public ResponseEntity<List<MovimentacaoEstoqueBodyDTO>> obterTodasMovimentacoes() {
        return ResponseEntity.ok().body(servico.obterTodasMovimentacoes());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity realizarMovimentacao(@RequestBody MovimentacaoEstoqueDTO movimentacao){
        return ResponseEntity.status(HttpStatus.OK).body(servico.realizarMovimentacao( movimentacao));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity cancelarMovimentacao(@PathVariable Long id) {
        servico.cancelarMovimentacao(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
