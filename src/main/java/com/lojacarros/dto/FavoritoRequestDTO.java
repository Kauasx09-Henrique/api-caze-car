package com.lojacarros.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoritoRequestDTO {

    @NotNull
    private Long usuarioId;

    @NotNull
    private Long veiculoId;
}