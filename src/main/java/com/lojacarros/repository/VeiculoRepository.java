package com.lojacarros.repository;

import com.lojacarros.entity.Veiculo;
import com.lojacarros.enums.StatusVeiculo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Optional<Veiculo> findByPlaca(String placa);

    boolean existsByPlaca(String placa);

    List<Veiculo> findByVendedorId(Long vendedorId);

    List<Veiculo> findByStatus(StatusVeiculo status);
}