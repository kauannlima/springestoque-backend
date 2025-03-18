package com.springestoque.springestoque_backend.controller;

import com.springestoque.springestoque_backend.domain.Fornecedor;
import com.springestoque.springestoque_backend.domain.dto.CategoriaBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.FornecedorBodyDTO;
import com.springestoque.springestoque_backend.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService servico;



    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<FornecedorBodyDTO>> obterTodosOsFornecedores() {
        return ResponseEntity.ok().body(servico.obterTodosOsFornecedores());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{nome}")
    public ResponseEntity<List<FornecedorBodyDTO>> obterFornecedoresPorNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(servico.obterFornecedoresPorNome(nome));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity cadastrarFornecedor(@RequestBody Fornecedor fornecedor){
        servico.cadastrarFornecedor(fornecedor);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity editarFornecedor(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
        servico.editarFornecedor(id, fornecedor);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity excluirFornecedor(@PathVariable Long id) {
        servico.excluirFornecedor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
