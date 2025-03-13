package com.springestoque.springestoque_backend.controller;

import com.springestoque.springestoque_backend.domain.dto.ProdutoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.ProdutoDTO;
import com.springestoque.springestoque_backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService servico;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<ProdutoBodyDTO>> obterTodosOsProdutos() {
        return ResponseEntity.ok().body(servico.obterTodosOsProdutos());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoBodyDTO> obterProdutoPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(servico.obterProdutoPorId(id));
    }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
   @PostMapping
    public ResponseEntity<ProdutoBodyDTO> cadastrarProduto(@RequestBody ProdutoDTO produto){
      ProdutoBodyDTO produtoDTO = servico.cadastrarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoDTO);
    }

}
