package com.lojacarros.repository;

import com.lojacarros.entity.Favorito;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    List<Favorito> findByUsuarioId(Long usuarioId);
    boolean existsByUsuarioIdAndVeiculoId(Long usuarioId, Long veiculoId);
    void deleteByUsuarioIdAndVeiculoId(Long usuarioId, Long veiculoId);
}