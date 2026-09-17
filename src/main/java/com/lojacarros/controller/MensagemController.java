package com.lojacarros.controller;

import com.lojacarros.dto.MensagemRequestDTO;
import com.lojacarros.dto.MensagemResponseDTO;
import com.lojacarros.service.MensagemService;
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
@RequestMapping("/mensagens")
public class MensagemController {

    private final MensagemService mensagemService;

    public MensagemController(MensagemService mensagemService) {
        this.mensagemService = mensagemService;
    }

    @PostMapping
    public ResponseEntity<MensagemResponseDTO> enviar(@RequestBody @Valid MensagemRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mensagemService.enviar(dto));
    }

    @GetMapping("/conversa/{conversaId}")
    public ResponseEntity<List<MensagemResponseDTO>> listarPorConversa(@PathVariable Long conversaId) {
        return ResponseEntity.ok(mensagemService.listarPorConversa(conversaId));
    }
}