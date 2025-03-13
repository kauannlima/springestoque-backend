package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.Funcionario;
import com.springestoque.springestoque_backend.domain.Usuario;
import com.springestoque.springestoque_backend.domain.dto.CargoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.UsuarioBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.UsuarioDTO;
import com.springestoque.springestoque_backend.exception.CargoNaoEncontradoException;
import com.springestoque.springestoque_backend.exception.FuncionarioNaoEncontradoException;
import com.springestoque.springestoque_backend.repository.FuncionarioRepository;
import com.springestoque.springestoque_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<CargoBodyDTO> obterCargosPorNome(String nome) {
        List<Cargo> cargos = repository.findAllByNomeContainingIgnoreCase(nome);

        if (cargos.isEmpty()) {
            throw new CargoNaoEncontradoException(nome);  // Exceção customizada
        }

        return cargos.stream()
                .map(CargoBodyDTO::new)  // Convertendo de Cargo para CargoBodyDTO
                .collect(Collectors.toList());  // Coletando em uma lista
    }

    public UsuarioBodyDTO cadastrarUsuario(UsuarioDTO dto){

        String senhaCriptografada = passwordEncoder.encode(dto.senha());

        Funcionario funcionario = funcionarioRepository.findById(dto.funcionario())
                .orElseThrow(() -> new FuncionarioNaoEncontradoException(dto.funcionario()));


        Usuario usuario = new Usuario(null, funcionario, dto.nomeDoUsuario(), dto.email(), senhaCriptografada);


        usuario = repository.save(usuario);

        return new UsuarioBodyDTO(funcionario.getNome(),usuario.getNomeDeUsuario());

    }
}
