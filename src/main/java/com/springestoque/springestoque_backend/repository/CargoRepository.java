package com.springestoque.springestoque_backend.repository;

import com.springestoque.springestoque_backend.domain.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long> {

    List<Cargo> findAllByNomeContainingIgnoreCase(String nome);


}
