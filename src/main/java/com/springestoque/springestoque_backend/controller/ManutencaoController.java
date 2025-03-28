package com.springestoque.springestoque_backend.controller;

import com.springestoque.springestoque_backend.domain.StatusManutencaoEnum;
import com.springestoque.springestoque_backend.domain.dto.*;
import com.springestoque.springestoque_backend.service.ManutencaoService;
import com.springestoque.springestoque_backend.service.MovimentacaoEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manutencao")
public class ManutencaoController {

    @Autowired
    private ManutencaoService servico;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping()
    public ResponseEntity<List<ManutencaoBodyDTO>> obterTodasManutencoes() {
        return ResponseEntity.ok().body(servico.obterTodasManutencoes());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("andamento")
    public ResponseEntity<List<ManutencaoBodyDTO>> obterTodasManutencoesEmAndamento() {
        return ResponseEntity.ok().body(servico.obterTodasManutencoesEmAndamento());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("concluida")
    public ResponseEntity<List<ManutencaoBodyDTO>> obterTodasManutencoesConcluidas() {
        return ResponseEntity.ok().body(servico.obterTodasManutencoesConcluidas());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("cancelada")
    public ResponseEntity<List<ManutencaoBodyDTO>> obterTodasManutencoesCanceladas() {
        return ResponseEntity.ok().body(servico.obterTodasManutencoesCanceladas());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity realizarMovimentacao(@RequestBody ManutencaoDTO manutencaoDTO){
        return ResponseEntity.status(HttpStatus.OK).body(servico.cadastrarManutencao(manutencaoDTO));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity realizarMovimentacao(@PathVariable Long id, @RequestBody StatusManutencaoDTO statusManutencaoDTO ){
        return ResponseEntity.status(HttpStatus.OK).body(servico.atualizarStatusManutencao(id, statusManutencaoDTO.status()));
    }


}
