package com.lojacarros.controller;

import com.lojacarros.dto.ConversaRequestDTO;
import com.lojacarros.dto.ConversaResponseDTO;
import com.lojacarros.service.ConversaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conversas")
public class ConversaController {

    private final ConversaService conversaService;

    public ConversaController(ConversaService conversaService) {
        this.conversaService = conversaService;
    }

    @PostMapping
    public ResponseEntity<ConversaResponseDTO> iniciarOuRecuperar(@RequestBody @Valid ConversaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(conversaService.iniciarOuRecuperar(dto));
    }

    @GetMapping("/comprador/{compradorId}")
    public ResponseEntity<List<ConversaResponseDTO>> listarPorComprador(@PathVariable Long compradorId) {
        return ResponseEntity.ok(conversaService.listarPorComprador(compradorId));
    }

    @GetMapping("/vendedor/{vendedorId}")
    public ResponseEntity<List<ConversaResponseDTO>> listarPorVendedor(@PathVariable Long vendedorId) {
        return ResponseEntity.ok(conversaService.listarPorVendedor(vendedorId));
    }
}