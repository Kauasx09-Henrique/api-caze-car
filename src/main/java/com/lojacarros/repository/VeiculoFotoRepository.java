package com.lojacarros.repository;

import com.lojacarros.entity.VeiculoFoto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoFotoRepository extends JpaRepository<VeiculoFoto, Long> {

    List<VeiculoFoto> findByVeiculoIdOrderByOrdemAsc(Long veiculoId);
}