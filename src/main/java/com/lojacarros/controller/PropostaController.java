package com.lojacarros.controller;

import com.lojacarros.dto.PropostaRequestDTO;
import com.lojacarros.dto.PropostaResponseDTO;
import com.lojacarros.enums.StatusProposta;
import com.lojacarros.service.PropostaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private final PropostaService propostaService;

    public PropostaController(PropostaService propostaService) {
        this.propostaService = propostaService;
    }

    @PostMapping
    public ResponseEntity<PropostaResponseDTO> criar(@RequestBody @Valid PropostaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(propostaService.criar(dto));
    }

    @GetMapping("/veiculo/{veiculoId}")
    public ResponseEntity<List<PropostaResponseDTO>> listarPorVeiculo(@PathVariable Long veiculoId) {
        return ResponseEntity.ok(propostaService.listarPorVeiculo(veiculoId));
    }

    @GetMapping("/comprador/{compradorId}")
    public ResponseEntity<List<PropostaResponseDTO>> listarPorComprador(@PathVariable Long compradorId) {
        return ResponseEntity.ok(propostaService.listarPorComprador(compradorId));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PropostaResponseDTO> atualizarStatus(@PathVariable Long id, @RequestParam StatusProposta status) {
        return ResponseEntity.ok(propostaService.atualizarStatus(id, status));
    }
}