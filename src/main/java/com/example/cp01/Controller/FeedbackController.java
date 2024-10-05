package com.example.cp01.Controller;


import com.example.cp01.Entidade.Dtos.FeedbackDTO;
import com.example.cp01.Entidade.Feedback;
import com.example.cp01.Repository.FeedbackRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Feedback")
public class FeedbackController {

    @Autowired
    FeedbackRepository feedbackRepository;

    @PostMapping
    public ResponseEntity<Feedback> create(@RequestBody Feedback feedback) {
        return new ResponseEntity<>(feedbackRepository.save(feedback), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> get(@PathVariable Long id) {
        return feedbackRepository.findById(id)
                .map(feedback -> ResponseEntity.ok(feedback))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Feedback>> list(Pageable pageable) {
        return ResponseEntity.ok(feedbackRepository.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feedback> update(@PathVariable Long id, @Valid @RequestBody FeedbackDTO feedbackDTO) {
        Optional<Feedback> optionalFeedback = feedbackRepository.findById(id);
        if (optionalFeedback.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Feedback feedbackExiste = optionalFeedback.get();
        feedbackExiste.setId(feedbackDTO.id());
        feedbackExiste.setPacienteId(feedbackDTO.pacienteId());
        feedbackExiste.setData(feedbackDTO.data());
        feedbackExiste.setNota(feedbackDTO.nota());
        feedbackExiste.setComentario(feedbackDTO.comentario());

        return ResponseEntity.ok(feedbackRepository.save(feedbackExiste));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!feedbackRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        feedbackRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

