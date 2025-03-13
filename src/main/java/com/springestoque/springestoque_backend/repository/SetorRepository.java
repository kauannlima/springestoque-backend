package com.springestoque.springestoque_backend.repository;

import com.springestoque.springestoque_backend.domain.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {

    List<Setor> findAllByNomeContainingIgnoreCase(String nome);
}