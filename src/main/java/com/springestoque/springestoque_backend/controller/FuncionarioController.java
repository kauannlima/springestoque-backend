package com.springestoque.springestoque_backend.controller;

import com.springestoque.springestoque_backend.domain.Funcionario;
import com.springestoque.springestoque_backend.domain.dto.FornecedorBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.FuncionarioBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.FuncionarioDTO;
import com.springestoque.springestoque_backend.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService servico;



    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<FuncionarioBodyDTO>> obterTodosOsFuncionarios() {
        return ResponseEntity.ok().body(servico.obterTodosOsFuncionarios());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{nome}")
    public ResponseEntity<List<FuncionarioBodyDTO>> obterFuncionariosPorNome(@PathVariable String nome) {
        return ResponseEntity.ok().body(servico.obterFuncionariosPorNome(nome));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity cadastrarFuncionario(@RequestBody FuncionarioDTO dto){
        FuncionarioBodyDTO funcionarioDTO =  servico.cadastrarFuncionario(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity editarFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        servico.editarFuncionario(id, funcionario);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity excluirFuncionario(@PathVariable Long id) {
        servico.excluirFuncionario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
