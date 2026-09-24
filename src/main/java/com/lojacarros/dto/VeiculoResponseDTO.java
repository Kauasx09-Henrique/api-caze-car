package com.lojacarros.dto;

import com.lojacarros.enums.StatusVeiculo;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoResponseDTO {

    private Long id;
    private Long vendedorId;
    private Long modeloId;
    private Long combustivelId;
    private Long cambioId;
    private Long carroceriaId;
    private Long corId;
    private Short anoFabricacao;
    private Short anoModelo;
    private Integer quilometragem;
    private BigDecimal preco;
    private String placa;
    private String descricao;
    private StatusVeiculo status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Nomes resolvidos para exibição direta no front, sem precisar de outra chamada
    private String marca;
    private String modeloNome;
    private String corNome;
    private String combustivelNome;
    private String cambioNome;
    private String carroceriaNome;
    private String imagem;
    private List<String> imagens;
}