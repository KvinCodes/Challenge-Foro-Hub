package com.alura.forohub.domain.topic.dto;

import jakarta.validation.constraints.NotBlank;

public record TopicCreateDTO(

        @NotBlank(message = "El título es obligatorio")
        String titulo,

        @NotBlank(message = "El mensaje es obligatorio")
        String mensaje,

        @NotBlank(message = "El autor es obligatorio")
        String autor,

        @NotBlank(message = "El curso es obligatorio")
        String curso
) {}
