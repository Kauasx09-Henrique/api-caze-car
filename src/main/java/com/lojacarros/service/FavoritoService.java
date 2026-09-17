package com.lojacarros.service;

import com.lojacarros.dto.FavoritoRequestDTO;
import com.lojacarros.dto.FavoritoResponseDTO;
import com.lojacarros.entity.Favorito;
import com.lojacarros.entity.Usuario;
import com.lojacarros.entity.Veiculo;
import com.lojacarros.repository.FavoritoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;

    public FavoritoService(FavoritoRepository favoritoRepository) {
        this.favoritoRepository = favoritoRepository;
    }

    @Transactional
    public FavoritoResponseDTO adicionar(FavoritoRequestDTO dto) {
        if (favoritoRepository.existsByUsuarioIdAndVeiculoId(dto.getUsuarioId(), dto.getVeiculoId())) {
            throw new RuntimeException("Veículo já favoritado por este usuário");
        }

        Favorito favorito = new Favorito();

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());
        favorito.setUsuario(usuario);

        Veiculo veiculo = new Veiculo();
        veiculo.setId(dto.getVeiculoId());
        favorito.setVeiculo(veiculo);

        Favorito salvo = favoritoRepository.save(favorito);
        return converterParaDTO(salvo);
    }

    public List<FavoritoResponseDTO> listarPorUsuario(Long usuarioId) {
        return favoritoRepository.findByUsuarioId(usuarioId).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void remover(Long usuarioId, Long veiculoId) {
        favoritoRepository.deleteByUsuarioIdAndVeiculoId(usuarioId, veiculoId);
    }

    private FavoritoResponseDTO converterParaDTO(Favorito favorito) {
        FavoritoResponseDTO dto = new FavoritoResponseDTO();
        BeanUtils.copyProperties(favorito, dto);
        if (favorito.getUsuario() != null) dto.setUsuarioId(favorito.getUsuario().getId());
        if (favorito.getVeiculo() != null) dto.setVeiculoId(favorito.getVeiculo().getId());
        return dto;
    }
}