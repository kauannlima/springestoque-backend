package com.springestoque.springestoque_backend.repository;

import com.springestoque.springestoque_backend.domain.Setor;
import com.springestoque.springestoque_backend.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findAllByNomeDeUsuarioContainingIgnoreCase(String nome);
}