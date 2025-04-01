package com.springestoque.springestoque_backend.controller;

import com.springestoque.springestoque_backend.domain.Usuario;
import com.springestoque.springestoque_backend.domain.dto.FuncionarioDTO;
import com.springestoque.springestoque_backend.domain.dto.SetorBodyDTO;
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
    @GetMapping
    public ResponseEntity<List<UsuarioBodyDTO>> obterTodosOsUsuarios() {
        return ResponseEntity.ok().body(servico.obterTodosOsUsuarios());
    }

//    @CrossOrigin(origins = "*", allowedHeaders = "*")
//    @GetMapping("/{nome}")
//    public ResponseEntity<List<UsuarioBodyDTO>> obterUsuariosPorNome(@PathVariable String nome) {
//        return ResponseEntity.ok().body(servico.obterUsuariosPorNome(nome));
//    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity cadastrarUsuario(@RequestBody UsuarioDTO dto){
        UsuarioBodyDTO usuarioBodyDTO =  servico.cadastrarUsuario(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioBodyDTO);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        servico.editarUsuario(id, usuario);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity excluirUsuario(@PathVariable Long id) {
        servico.excluirUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
