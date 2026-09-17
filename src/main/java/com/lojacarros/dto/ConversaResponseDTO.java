package com.lojacarros.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversaResponseDTO {

    private Long id;
    private Long veiculoId;
    private Long compradorId;
    private Long vendedorId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}