package com.springestoque.springestoque_backend.controller;

import com.springestoque.springestoque_backend.domain.dto.FuncionarioDTO;
import com.springestoque.springestoque_backend.domain.dto.UsuarioBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.UsuarioDTO;
import com.springestoque.springestoque_backend.service.FuncionarioService;
import com.springestoque.springestoque_backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService servico;

  @CrossOrigin(origins = "*", allowedHeaders = "*")
   @PostMapping
    public ResponseEntity cadastrarUsuario(@RequestBody UsuarioDTO dto){
        UsuarioBodyDTO usuarioBodyDTO =  servico.cadastrarUsuario(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioBodyDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<UsuarioBodyDTO>> obterTodosOsUsuarios() {
        return ResponseEntity.ok().body(servico.obterTodosOsUsuarios());
    }

}
