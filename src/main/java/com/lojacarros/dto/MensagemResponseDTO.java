package com.lojacarros.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensagemResponseDTO {

    private Long id;
    private Long conversaId;
    private Long remetenteId;
    private String conteudo;
    private Boolean lida;
    private LocalDateTime createdAt;
}