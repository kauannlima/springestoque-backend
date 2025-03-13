package com.springestoque.springestoque_backend.repository;

import com.springestoque.springestoque_backend.domain.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    List<Fornecedor> findAllByNomeContainingIgnoreCase(String nome);
}