package com.lojacarros.dto;

import com.lojacarros.enums.TipoUsuario;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private TipoUsuario tipoUsuario;
    private Boolean ativo;
    private LocalDateTime createdAt;
}