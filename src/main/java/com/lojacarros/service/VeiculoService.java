package com.lojacarros.service;

import com.lojacarros.dto.VeiculoRequestDTO;
import com.lojacarros.dto.VeiculoResponseDTO;
import com.lojacarros.entity.Cambio;
import com.lojacarros.entity.Carroceria;
import com.lojacarros.entity.Combustivel;
import com.lojacarros.entity.Cor;
import com.lojacarros.entity.Modelo;
import com.lojacarros.entity.Usuario;
import com.lojacarros.entity.Veiculo;
import com.lojacarros.repository.UsuarioRepository;
import com.lojacarros.repository.VeiculoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final UsuarioRepository usuarioRepository;

    public VeiculoService(VeiculoRepository veiculoRepository, UsuarioRepository usuarioRepository) {
        this.veiculoRepository = veiculoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public VeiculoResponseDTO criar(VeiculoRequestDTO dto) {
        if (veiculoRepository.existsByPlaca(dto.getPlaca())) {
            throw new RuntimeException("Placa já cadastrada");
        }

        Usuario vendedor = usuarioRepository.findById(dto.getVendedorId())
                .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));

        Veiculo veiculo = new Veiculo();
        BeanUtils.copyProperties(dto, veiculo);

        veiculo.setVendedor(vendedor);

        Modelo modelo = new Modelo();
        modelo.setId(dto.getModeloId());
        veiculo.setModelo(modelo);

        Combustivel combustivel = new Combustivel();
        combustivel.setId(dto.getCombustivelId());
        veiculo.setCombustivel(combustivel);

        Cambio cambio = new Cambio();
        cambio.setId(dto.getCambioId());
        veiculo.setCambio(cambio);

        Carroceria carroceria = new Carroceria();
        carroceria.setId(dto.getCarroceriaId());
        veiculo.setCarroceria(carroceria);

        Cor cor = new Cor();
        cor.setId(dto.getCorId());
        veiculo.setCor(cor);

        Veiculo salvo = veiculoRepository.save(veiculo);
        return converterParaDTO(salvo);
    }

    public List<VeiculoResponseDTO> listarTodos() {
        return veiculoRepository.findAll().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    private VeiculoResponseDTO converterParaDTO(Veiculo veiculo) {
        VeiculoResponseDTO dto = new VeiculoResponseDTO();
        BeanUtils.copyProperties(veiculo, dto);

        if (veiculo.getVendedor() != null) dto.setVendedorId(veiculo.getVendedor().getId());
        if (veiculo.getModelo() != null) dto.setModeloId(veiculo.getModelo().getId());
        if (veiculo.getCombustivel() != null) dto.setCombustivelId(veiculo.getCombustivel().getId());
        if (veiculo.getCambio() != null) dto.setCambioId(veiculo.getCambio().getId());
        if (veiculo.getCarroceria() != null) dto.setCarroceriaId(veiculo.getCarroceria().getId());
        if (veiculo.getCor() != null) dto.setCorId(veiculo.getCor().getId());

        return dto;
    }
}