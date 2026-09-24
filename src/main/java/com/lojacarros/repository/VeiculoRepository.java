package com.lojacarros.repository;

import com.lojacarros.entity.Veiculo;
import com.lojacarros.enums.StatusVeiculo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Optional<Veiculo> findByPlaca(String placa);

    boolean existsByPlaca(String placa);

    List<Veiculo> findByVendedorId(Long vendedorId);

    List<Veiculo> findByStatus(StatusVeiculo status);

    // JOIN FETCH traz marca/modelo/cor/etc. já carregados na mesma consulta,
    // necessário porque open-in-view está desabilitado (sessão fecha antes da conversão para DTO).
    @Query("""
            SELECT v FROM Veiculo v
            JOIN FETCH v.modelo m
            JOIN FETCH m.marca
            JOIN FETCH v.cor
            JOIN FETCH v.combustivel
            JOIN FETCH v.cambio
            JOIN FETCH v.carroceria
            """)
    List<Veiculo> findAllComDetalhes();
}