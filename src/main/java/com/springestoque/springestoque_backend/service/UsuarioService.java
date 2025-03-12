package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Funcionario;
import com.springestoque.springestoque_backend.domain.Usuario;
import com.springestoque.springestoque_backend.domain.dto.UsuarioBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.UsuarioDTO;
import com.springestoque.springestoque_backend.exception.FuncionarioNaoEncontradoException;
import com.springestoque.springestoque_backend.repository.FuncionarioRepository;
import com.springestoque.springestoque_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UsuarioBodyDTO cadastrarUsuario(UsuarioDTO dto){

        String senhaCriptografada = passwordEncoder.encode(dto.senha());

        Funcionario funcionario = funcionarioRepository.findById(dto.funcionario())
                .orElseThrow(() -> new FuncionarioNaoEncontradoException(dto.funcionario()));


        Usuario usuario = new Usuario(null, funcionario, dto.nomeDoUsuario(), dto.email(), senhaCriptografada);


        usuario = repository.save(usuario);

        return new UsuarioBodyDTO(funcionario.getNome(),usuario.getNomeDeUsuario());

    }

    public List<UsuarioBodyDTO> obterTodosOsUsuarios() {

        return repository.findAll().stream().map(UsuarioBodyDTO::new).toList();
    }
}
