package com.lojacarros.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConversaRequestDTO {

    @NotNull
    private Long veiculoId;

    @NotNull
    private Long compradorId;

    @NotNull
    private Long vendedorId;
}