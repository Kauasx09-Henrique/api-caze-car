package com.lojacarros.repository;

import com.lojacarros.entity.Proposta;
import com.lojacarros.enums.StatusProposta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    List<Proposta> findByCompradorId(Long compradorId);
    List<Proposta> findByVeiculoId(Long veiculoId);
    List<Proposta> findByVeiculoIdAndStatus(Long veiculoId, StatusProposta status);
}