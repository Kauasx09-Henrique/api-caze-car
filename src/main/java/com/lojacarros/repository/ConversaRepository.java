package com.lojacarros.repository;

import com.lojacarros.entity.Conversa;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversaRepository extends JpaRepository<Conversa, Long> {
    List<Conversa> findByCompradorId(Long compradorId);
    List<Conversa> findByVendedorId(Long vendedorId);
    Optional<Conversa> findByVeiculoIdAndCompradorId(Long veiculoId, Long compradorId);
}