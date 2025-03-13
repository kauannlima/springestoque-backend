package com.springestoque.springestoque_backend.repository;

import com.springestoque.springestoque_backend.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    List<Funcionario> findAllByNomeContainingIgnoreCase(String nome);
}