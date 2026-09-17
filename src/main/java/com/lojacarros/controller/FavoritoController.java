package com.lojacarros.controller;

import com.lojacarros.dto.FavoritoRequestDTO;
import com.lojacarros.dto.FavoritoResponseDTO;
import com.lojacarros.service.FavoritoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    private final FavoritoService favoritoService;

    public FavoritoController(FavoritoService favoritoService) {
        this.favoritoService = favoritoService;
    }

    @PostMapping
    public ResponseEntity<FavoritoResponseDTO> adicionar(@RequestBody @Valid FavoritoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(favoritoService.adicionar(dto));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<FavoritoResponseDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(favoritoService.listarPorUsuario(usuarioId));
    }

    @DeleteMapping("/usuario/{usuarioId}/veiculo/{veiculoId}")
    public ResponseEntity<Void> remover(@PathVariable Long usuarioId, @PathVariable Long veiculoId) {
        favoritoService.remover(usuarioId, veiculoId);
        return ResponseEntity.noContent().build();
    }
}