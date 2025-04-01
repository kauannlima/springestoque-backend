package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.Funcionario;
import com.springestoque.springestoque_backend.domain.Setor;
import com.springestoque.springestoque_backend.domain.Usuario;
import com.springestoque.springestoque_backend.domain.dto.*;
import com.springestoque.springestoque_backend.exception.*;
import com.springestoque.springestoque_backend.repository.FuncionarioRepository;
import com.springestoque.springestoque_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    public List<UsuarioBodyDTO> obterTodosOsUsuarios() {

        return repository.findAll().stream().map(UsuarioBodyDTO::new).toList();
    }

// CADASTRAR NO FUTURO UM GET QUE BUSCA USUARIOS PELO NOME DE FUNCIONARIO
//    public List<UsuarioBodyDTO> obterUsuariosPorNome(String nome) {
//        List<Usuario> usuarios = repository.findAllByNomeDeUsuarioContainingIgnoreCase(nome);
//
//        if (usuarios.isEmpty()) {
//            throw new UsuarioNaoEncontradoException(nome);  // Exceção customizada
//        }
//
//        return usuarios.stream()
//                .map(UsuarioBodyDTO::new)  // Convertendo de Cargo para CargoBodyDTO
//                .collect(Collectors.toList());  // Coletando em uma lista
//    }

        public UsuarioLoginDTO obterUsuariosPorNomeDeUsuario(String nomeDeUsuario) {
        Usuario usuario = repository.findByNomeDeUsuario(nomeDeUsuario);

       return new UsuarioLoginDTO(usuario.getNomeDeUsuario(),usuario.getSenha());
    }



    public Usuario obterUsuarioPorId(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
        return usuario;
    }

    public UsuarioBodyDTO cadastrarUsuario(UsuarioDTO dto) {
        String senhaCriptografada = passwordEncoder.encode(dto.senha());

        Funcionario funcionario = funcionarioRepository.findById(dto.funcionario())
                .orElseThrow(() -> new FuncionarioNaoEncontradoException(dto.funcionario()));

        Usuario usuario = new Usuario(null, funcionario, dto.nomeDoUsuario(), dto.email(), senhaCriptografada);

        usuario = repository.save(usuario);

        return new UsuarioBodyDTO(usuario.getId(), usuario.getNomeDeUsuario(), usuario.getEmail());
    }

    public void realizarLogin(UsuarioLoginDTO dto){
        UsuarioLoginDTO Usuariodto = obterUsuariosPorNomeDeUsuario(dto.nomeDoUsuario());

    }

    var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);
    var token = tokenService.generateToken((User) auth.getPrincipal());
    String role = ((User) auth.getPrincipal()).getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .findFirst()
            .orElse("USER");
        return  ResponseEntity.ok(new LoginResponseDTO(token, role));


    public void editarUsuario(Long id, Usuario usuario) {
        Usuario usuarioBuscado = obterUsuarioPorId(id);

        if (usuario.getNomeDeUsuario() != null && !usuario.getNomeDeUsuario().isBlank()) {
            usuarioBuscado.setNomeDeUsuario(usuario.getNomeDeUsuario());
        }
        if (usuario.getEmail() != null && !usuario.getEmail().isBlank()) {
            usuarioBuscado.setEmail(usuario.getEmail());
        }
        if (usuario.getSenha() != null && !usuario.getSenha().isBlank()) {
            usuarioBuscado.setSenha(passwordEncoder.encode(usuario.getSenha()));
        }

        repository.save(usuarioBuscado);
    }

    public void excluirUsuario(Long id) {
        Usuario usuario = obterUsuarioPorId(id);

        // Desvincula o funcionário antes de excluir o usuário
        if (usuario.getFuncionario() != null) {
            usuario.setFuncionario(null);  // Desvincula o funcionário
        }

        try {
            // Agora o usuário pode ser excluído sem problema
            repository.delete(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new EntidadeVinculadaException("Erro ao tentar excluir o usuário.");
        }
    }


}
