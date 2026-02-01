package com.alura.forohub.domain.topic;

import com.alura.forohub.domain.topic.dto.TopicCreateDTO;
import com.alura.forohub.domain.topic.dto.TopicUpdateDTO;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "topics")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 1000)
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Enumerated(EnumType.STRING)
    private StatusTopic status;

    private String autor;

    private String curso;

    // Constructor vacío requerido por JPA
    public Topic() {}

    // Constructor base
    public Topic(TopicCreateDTO dto) {
        this.titulo = dto.titulo();
        this.mensaje = dto.mensaje();
        this.autor = dto.autor();
        this.curso = dto.curso();
        this.fechaCreacion = LocalDateTime.now();
        this.status = StatusTopic.OPEN;
    }

    // Actualizar topicos
    public void update(TopicUpdateDTO dto) {
        this.titulo = dto.titulo();
        this.mensaje = dto.mensaje();
        this.autor = dto.autor();
        this.curso = dto.curso();
    }


    // Getters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public StatusTopic getStatus() {
        return status;
    }

    public String getAutor() {
        return autor;
    }

    public String getCurso() {
        return curso;
    }
}
