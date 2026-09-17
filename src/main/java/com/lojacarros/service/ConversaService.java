package com.lojacarros.service;

import com.lojacarros.dto.ConversaRequestDTO;
import com.lojacarros.dto.ConversaResponseDTO;
import com.lojacarros.entity.Conversa;
import com.lojacarros.entity.Usuario;
import com.lojacarros.entity.Veiculo;
import com.lojacarros.repository.ConversaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ConversaService {

    private final ConversaRepository conversaRepository;

    public ConversaService(ConversaRepository conversaRepository) {
        this.conversaRepository = conversaRepository;
    }

    public ConversaResponseDTO iniciarOuRecuperar(ConversaRequestDTO dto) {
        Optional<Conversa> conversaExistente = conversaRepository
                .findByVeiculoIdAndCompradorId(dto.getVeiculoId(), dto.getCompradorId());

        if (conversaExistente.isPresent()) {
            return converterParaDTO(conversaExistente.get());
        }

        Conversa conversa = new Conversa();

        Veiculo veiculo = new Veiculo();
        veiculo.setId(dto.getVeiculoId());
        conversa.setVeiculo(veiculo);

        Usuario comprador = new Usuario();
        comprador.setId(dto.getCompradorId());
        conversa.setComprador(comprador);

        Usuario vendedor = new Usuario();
        vendedor.setId(dto.getVendedorId());
        conversa.setVendedor(vendedor);

        Conversa salva = conversaRepository.save(conversa);
        return converterParaDTO(salva);
    }

    public List<ConversaResponseDTO> listarPorComprador(Long compradorId) {
        return conversaRepository.findByCompradorId(compradorId).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    public List<ConversaResponseDTO> listarPorVendedor(Long vendedorId) {
        return conversaRepository.findByVendedorId(vendedorId).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    private ConversaResponseDTO converterParaDTO(Conversa conversa) {
        ConversaResponseDTO dto = new ConversaResponseDTO();
        BeanUtils.copyProperties(conversa, dto);

        if (conversa.getVeiculo() != null) dto.setVeiculoId(conversa.getVeiculo().getId());
        if (conversa.getComprador() != null) dto.setCompradorId(conversa.getComprador().getId());
        if (conversa.getVendedor() != null) dto.setVendedorId(conversa.getVendedor().getId());

        return dto;
    }
}