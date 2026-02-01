package com.alura.forohub.controller;

import com.alura.forohub.domain.topic.Topic;
import com.alura.forohub.domain.topic.TopicRepository;
import com.alura.forohub.domain.topic.dto.TopicCreateDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.alura.forohub.domain.topic.dto.TopicListDTO;
import com.alura.forohub.domain.topic.dto.TopicDetailDTO;
import com.alura.forohub.domain.topic.dto.TopicUpdateDTO;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/topicos")
public class TopicController {

    private final TopicRepository repository;

    public TopicController(TopicRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> createTopic(@RequestBody @Valid TopicCreateDTO dto) {

        // no permitir duplicados
        if (repository.existsByTituloAndMensaje(dto.titulo(), dto.mensaje())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Ya existe un tópico con el mismo título y mensaje");
        }

        Topic topic = new Topic(dto);
        repository.save(topic);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<TopicListDTO>> listTopics() {

        List<TopicListDTO> topics = repository.findAll()
                .stream()
                .map(TopicListDTO::new)
                .toList();

        return ResponseEntity.ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailTopic(@PathVariable Long id) {

        Optional<Topic> topicOptional = repository.findById(id);

        if (topicOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        TopicDetailDTO dto = new TopicDetailDTO(topicOptional.get());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateTopic(
            @PathVariable Long id,
            @RequestBody @Valid TopicUpdateDTO dto
    ) {

        Optional<Topic> topicOptional = repository.findById(id);

        // Regla: el tópico debe existir
        if (topicOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Regla: no permitir duplicados
        if (repository.existsByTituloAndMensaje(dto.titulo(), dto.mensaje())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Ya existe un tópico con el mismo título y mensaje");
        }

        Topic topic = topicOptional.get();
        topic.update(dto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteTopic(@PathVariable Long id) {

        Optional<Topic> topicOptional = repository.findById(id);

        // Regla: el tópico debe existir
        if (topicOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
