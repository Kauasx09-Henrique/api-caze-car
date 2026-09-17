package com.lojacarros.dto;

import com.lojacarros.enums.StatusProposta;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropostaResponseDTO {

    private Long id;
    private Long veiculoId;
    private Long compradorId;
    private BigDecimal valor;
    private StatusProposta status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}