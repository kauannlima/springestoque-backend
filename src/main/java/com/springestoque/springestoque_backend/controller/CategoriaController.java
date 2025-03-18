package com.springestoque.springestoque_backend.controller;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.Categoria;
import com.springestoque.springestoque_backend.domain.dto.CargoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.CategoriaBodyDTO;
import com.springestoque.springestoque_backend.service.CategoriaService;
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
    @GetMapping
    public ResponseEntity<List<CategoriaBodyDTO>> obterTodasAsCategorias() {
        return ResponseEntity.ok().body(servico.obterTodasAsCategorias());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{nome}")
    public ResponseEntity<List<CategoriaBodyDTO>> obterCategoriasPorNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(servico.obterCategoriasPorNome(nome));
    }

  @CrossOrigin(origins = "*", allowedHeaders = "*")
   @PostMapping
    public ResponseEntity cadastrarCategoria(@RequestBody Categoria categoria){
             servico.cadastrarCategoria(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity editarCategoria(@PathVariable Long id, @RequestBody Categoria categoria){
        servico.editarCategoria(id, categoria);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity excluirCategoria(@PathVariable Long id) {
        servico.excluirCategoria(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
