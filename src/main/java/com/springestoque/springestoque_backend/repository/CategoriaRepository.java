package com.springestoque.springestoque_backend.repository;

import com.springestoque.springestoque_backend.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findAllByNomeContainingIgnoreCase(String nome);
}