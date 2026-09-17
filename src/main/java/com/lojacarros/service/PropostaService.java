package com.lojacarros.service;

import com.lojacarros.dto.PropostaRequestDTO;
import com.lojacarros.dto.PropostaResponseDTO;
import com.lojacarros.entity.Proposta;
import com.lojacarros.entity.Usuario;
import com.lojacarros.entity.Veiculo;
import com.lojacarros.enums.StatusProposta;
import com.lojacarros.repository.PropostaRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PropostaService {

    private final PropostaRepository propostaRepository;

    public PropostaService(PropostaRepository propostaRepository) {
        this.propostaRepository = propostaRepository;
    }

    public PropostaResponseDTO criar(PropostaRequestDTO dto) {
        Proposta proposta = new Proposta();
        BeanUtils.copyProperties(dto, proposta);

        Veiculo veiculo = new Veiculo();
        veiculo.setId(dto.getVeiculoId());
        proposta.setVeiculo(veiculo);

        Usuario comprador = new Usuario();
        comprador.setId(dto.getCompradorId());
        proposta.setComprador(comprador);

        proposta.setStatus(StatusProposta.PENDENTE);

        Proposta salva = propostaRepository.save(proposta);
        return converterParaDTO(salva);
    }

    public List<PropostaResponseDTO> listarPorVeiculo(Long veiculoId) {
        return propostaRepository.findByVeiculoId(veiculoId).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<PropostaResponseDTO> listarPorComprador(Long compradorId) {
        return propostaRepository.findByCompradorId(compradorId).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public PropostaResponseDTO atualizarStatus(Long id, StatusProposta status) {
        Proposta proposta = propostaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proposta não encontrada"));

        proposta.setStatus(status);
        Proposta atualizada = propostaRepository.save(proposta);
        return converterParaDTO(atualizada);
    }

    private PropostaResponseDTO converterParaDTO(Proposta proposta) {
        PropostaResponseDTO dto = new PropostaResponseDTO();
        BeanUtils.copyProperties(proposta, dto);

        if (proposta.getVeiculo() != null) dto.setVeiculoId(proposta.getVeiculo().getId());
        if (proposta.getComprador() != null) dto.setCompradorId(proposta.getComprador().getId());

        return dto;
    }
}