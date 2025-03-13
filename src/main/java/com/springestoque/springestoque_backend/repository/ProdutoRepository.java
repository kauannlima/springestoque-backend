package com.springestoque.springestoque_backend.repository;

import com.springestoque.springestoque_backend.domain.Funcionario;
import com.springestoque.springestoque_backend.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findAllByNomeContainingIgnoreCase(String nome);
}

