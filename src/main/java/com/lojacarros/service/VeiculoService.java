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
import com.lojacarros.entity.VeiculoFoto;
import com.lojacarros.repository.UsuarioRepository;
import com.lojacarros.repository.VeiculoFotoRepository;
import com.lojacarros.repository.VeiculoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final UsuarioRepository usuarioRepository;
    private final VeiculoFotoRepository veiculoFotoRepository;

    public VeiculoService(
            VeiculoRepository veiculoRepository,
            UsuarioRepository usuarioRepository,
            VeiculoFotoRepository veiculoFotoRepository) {
        this.veiculoRepository = veiculoRepository;
        this.usuarioRepository = usuarioRepository;
        this.veiculoFotoRepository = veiculoFotoRepository;
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
        return veiculoRepository.findAllComDetalhes().stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public VeiculoResponseDTO buscarPorId(Long id) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado com o ID: " + id));

        return converterParaDTO(veiculo);
    }

    private VeiculoResponseDTO converterParaDTO(Veiculo veiculo) {
        VeiculoResponseDTO dto = new VeiculoResponseDTO();
        BeanUtils.copyProperties(veiculo, dto);

        if (veiculo.getVendedor() != null) dto.setVendedorId(veiculo.getVendedor().getId());

        if (veiculo.getModelo() != null) {
            dto.setModeloId(veiculo.getModelo().getId());
            dto.setModeloNome(veiculo.getModelo().getNome());
            if (veiculo.getModelo().getMarca() != null) {
                dto.setMarca(veiculo.getModelo().getMarca().getNome());
            }
        }
        if (veiculo.getCombustivel() != null) {
            dto.setCombustivelId(veiculo.getCombustivel().getId());
            dto.setCombustivelNome(veiculo.getCombustivel().getNome());
        }
        if (veiculo.getCambio() != null) {
            dto.setCambioId(veiculo.getCambio().getId());
            dto.setCambioNome(veiculo.getCambio().getNome());
        }
        if (veiculo.getCarroceria() != null) {
            dto.setCarroceriaId(veiculo.getCarroceria().getId());
            dto.setCarroceriaNome(veiculo.getCarroceria().getNome());
        }
        if (veiculo.getCor() != null) {
            dto.setCorId(veiculo.getCor().getId());
            dto.setCorNome(veiculo.getCor().getNome());
        }

        List<String> urls = veiculoFotoRepository.findByVeiculoIdOrderByOrdemAsc(veiculo.getId()).stream()
                .map(VeiculoFoto::getUrl)
                .collect(Collectors.toList());
        dto.setImagens(urls);
        dto.setImagem(urls.isEmpty() ? null : urls.get(0));

        return dto;
    }
}