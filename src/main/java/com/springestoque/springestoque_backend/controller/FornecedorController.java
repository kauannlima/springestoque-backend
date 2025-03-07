package com.springestoque.springestoque_backend.controller;

import com.springestoque.springestoque_backend.domain.Categoria;
import com.springestoque.springestoque_backend.domain.Fornecedor;
import com.springestoque.springestoque_backend.domain.dto.FornecedorDTO;
import com.springestoque.springestoque_backend.domain.dto.ProdutoDTO;
import com.springestoque.springestoque_backend.service.CategoriaService;
import com.springestoque.springestoque_backend.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("fornecedor")
public class FornecedorController {

    @Autowired
    private FornecedorService servico;

  @CrossOrigin(origins = "*", allowedHeaders = "*")
   @PostMapping
    public ResponseEntity cadastrarFornecedor(@RequestBody Fornecedor fornecedor){
             servico.cadastrarFornecedor(fornecedor);

        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<FornecedorDTO>> obterTodosOsFornecedores() {
        return ResponseEntity.ok().body(servico.obterTodosOsFornecedores());
    }

}
