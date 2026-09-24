package com.lojacarros.dto;

import com.lojacarros.enums.StatusVeiculo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoRequestDTO {

    @NotNull
    private Long vendedorId;

    @NotNull
    private Long modeloId;

    @NotNull
    private Long combustivelId;

    @NotNull
    private Long cambioId;

    @NotNull
    private Long carroceriaId;

    @NotNull
    private Long corId;

    @NotNull
    private Short anoFabricacao;

    @NotNull
    private Short anoModelo;

    @NotNull
    @Min(0)
    private Integer quilometragem;

    @NotNull
    @Min(0)
    private BigDecimal preco;

    @NotBlank
    private String placa;

    private String descricao;

    @NotNull
    private StatusVeiculo status;
}