package com.lojacarros.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropostaRequestDTO {

    @NotNull
    private Long veiculoId;

    @NotNull
    private Long compradorId;

    @NotNull
    @Min(0)
    private BigDecimal valor;
}