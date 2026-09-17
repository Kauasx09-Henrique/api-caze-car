package com.lojacarros.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoritoResponseDTO {

    private Long id;
    private Long usuarioId;
    private Long veiculoId;
    private LocalDateTime createdAt;
}