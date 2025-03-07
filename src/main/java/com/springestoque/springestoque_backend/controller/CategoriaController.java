package com.springestoque.springestoque_backend.controller;

import com.springestoque.springestoque_backend.domain.Categoria;
import com.springestoque.springestoque_backend.domain.Produto;
import com.springestoque.springestoque_backend.domain.dto.CategoriaDTO;
import com.springestoque.springestoque_backend.domain.dto.FornecedorDTO;
import com.springestoque.springestoque_backend.domain.dto.ProdutoDTO;
import com.springestoque.springestoque_backend.service.CategoriaService;
import com.springestoque.springestoque_backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService servico;

  @CrossOrigin(origins = "*", allowedHeaders = "*")
   @PostMapping
    public ResponseEntity cadastrarCategoria(@RequestBody Categoria categoria){
             servico.cadastrarCategoria(categoria);
        return ResponseEntity.ok().build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> obterTodasAsCategorias() {
        return ResponseEntity.ok().body(servico.obterTodasAsCategorias());
    }

}
