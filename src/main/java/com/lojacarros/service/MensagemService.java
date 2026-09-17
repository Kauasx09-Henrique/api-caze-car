package com.lojacarros.service;

import com.lojacarros.dto.MensagemRequestDTO;
import com.lojacarros.dto.MensagemResponseDTO;
import com.lojacarros.entity.Conversa;
import com.lojacarros.entity.Mensagem;
import com.lojacarros.entity.Usuario;
import com.lojacarros.repository.MensagemRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MensagemService {

    private final MensagemRepository mensagemRepository;

    public MensagemService(MensagemRepository mensagemRepository) {
        this.mensagemRepository = mensagemRepository;
    }

    public MensagemResponseDTO enviar(MensagemRequestDTO dto) {
        Mensagem mensagem = new Mensagem();
        BeanUtils.copyProperties(dto, mensagem);

        Conversa conversa = new Conversa();
        conversa.setId(dto.getConversaId());
        mensagem.setConversa(conversa);

        Usuario remetente = new Usuario();
        remetente.setId(dto.getRemetenteId());
        mensagem.setRemetente(remetente);

        mensagem.setLida(false);

        Mensagem salva = mensagemRepository.save(mensagem);
        return converterParaDTO(salva);
    }

    public List<MensagemResponseDTO> listarPorConversa(Long conversaId) {
        return mensagemRepository.findByConversaIdOrderByCreatedAtAsc(conversaId).stream()
                .map(this::converterParaDTO)
                .collect(Collectors.toList());
    }

    private MensagemResponseDTO converterParaDTO(Mensagem mensagem) {
        MensagemResponseDTO dto = new MensagemResponseDTO();
        BeanUtils.copyProperties(mensagem, dto);

        if (mensagem.getConversa() != null) dto.setConversaId(mensagem.getConversa().getId());
        if (mensagem.getRemetente() != null) dto.setRemetenteId(mensagem.getRemetente().getId());

        return dto;
    }
}