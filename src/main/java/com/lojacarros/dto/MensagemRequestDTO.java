package com.lojacarros.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensagemRequestDTO {

    @NotNull
    private Long conversaId;

    @NotNull
    private Long remetenteId;

    @NotBlank
    private String conteudo;
}