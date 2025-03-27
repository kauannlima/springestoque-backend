package com.springestoque.springestoque_backend.service;

import com.springestoque.springestoque_backend.domain.Cargo;
import com.springestoque.springestoque_backend.domain.Funcionario;
import com.springestoque.springestoque_backend.domain.Produto;
import com.springestoque.springestoque_backend.domain.Setor;
import com.springestoque.springestoque_backend.domain.dto.CargoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.ProdutoBodyDTO;
import com.springestoque.springestoque_backend.domain.dto.SetorBodyDTO;
import com.springestoque.springestoque_backend.exception.CargoNaoEncontradoException;
import com.springestoque.springestoque_backend.exception.EntidadeVinculadaException;
import com.springestoque.springestoque_backend.exception.ProdutoNaoEncontradoException;
import com.springestoque.springestoque_backend.exception.SetorNaoEncontradoException;
import com.springestoque.springestoque_backend.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetorService {

    @Autowired
    private SetorRepository repository;

    public List<SetorBodyDTO> obterTodosOsSetores() {
        return repository.findAll().stream().map(SetorBodyDTO::new).toList();
    }

    //Metodo sera usado para facitar a busca no front atraves do nome
    public List<SetorBodyDTO> obterSetoresPorNome(String nome) {
        List<Setor> setores = repository.findAllByNomeContainingIgnoreCase(nome);

        if (setores.isEmpty()) {
            throw new SetorNaoEncontradoException(nome);  // Exceção customizada
        }

        return setores.stream()
                .map(SetorBodyDTO::new)  // Convertendo de Cargo para CargoBodyDTO
                .collect(Collectors.toList());  // Coletando em uma lista
    }

    public Setor obterSetorPorId(Long id) {
        Setor setor = repository.findById(id).orElseThrow(() -> new SetorNaoEncontradoException(id));
        return setor;
    }

    public void cadastrarSetor(Setor setor){
        repository.save(setor);
    }

    public void editarSetor(Long id, Setor setor) {
        Setor setorBuscado = obterSetorPorId(id);

        if (setor.getNome() != null && !setor.getNome().isBlank()) {
            setorBuscado.setNome(setor.getNome());
        }

        repository.save(setorBuscado);
    }


}
