package com.alura.forohub.domain.topic.dto;

import com.alura.forohub.domain.topic.StatusTopic;
import com.alura.forohub.domain.topic.Topic;

import java.time.LocalDateTime;

public record TopicDetailDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopic status,
        String autor,
        String curso
) {
    public TopicDetailDTO(Topic topic) {
        this(
                topic.getId(),
                topic.getTitulo(),
                topic.getMensaje(),
                topic.getFechaCreacion(),
                topic.getStatus(),
                topic.getAutor(),
                topic.getCurso()
        );
    }
}
