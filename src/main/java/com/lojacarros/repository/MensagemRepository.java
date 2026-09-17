package com.lojacarros.repository;

import com.lojacarros.entity.Mensagem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findByConversaIdOrderByCreatedAtAsc(Long conversaId);
    long countByConversaIdAndLidaFalseAndRemetenteIdNot(Long conversaId, Long usuarioId);
}