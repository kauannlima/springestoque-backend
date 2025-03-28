package com.springestoque.springestoque_backend.repository;

import com.springestoque.springestoque_backend.domain.Manutencao;
import com.springestoque.springestoque_backend.domain.StatusManutencaoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
    List<Manutencao> findByStatus(StatusManutencaoEnum status);
}
