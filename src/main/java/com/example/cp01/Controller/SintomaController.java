package com.example.cp01.Controller;

import com.example.cp01.Entidade.Dtos.SintomaDTO;
import com.example.cp01.Entidade.Sintoma;
import com.example.cp01.Repository.SintomaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/sintoma")
public class SintomaController {

    @Autowired
    SintomaRepository sintomaRepository;

    @PostMapping
    public ResponseEntity<Sintoma> create(@RequestBody Sintoma sintoma) {
        return new ResponseEntity<>(sintomaRepository.save(sintoma), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sintoma> get(@PathVariable Long id) {
        return sintomaRepository.findById(id)
                .map(sintoma -> ResponseEntity.ok(sintoma))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Sintoma>> list(Pageable pageable) {
        return ResponseEntity.ok(sintomaRepository.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sintoma> update(@PathVariable Long id, @Valid @RequestBody SintomaDTO sintomaDTO) {
        Optional<Sintoma> optionalSintoma = sintomaRepository.findById(id);
        if (optionalSintoma.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Sintoma sintomaExiste = optionalSintoma.get();
        sintomaExiste.setId(sintomaDTO.id());
        sintomaExiste.setPacienteId(sintomaDTO.pacienteId());
        sintomaExiste.setData(sintomaDTO.data());
        sintomaExiste.setDescricao(sintomaDTO.descricao());
        sintomaExiste.setGravidade(sintomaDTO.gravidade());

        return ResponseEntity.ok(sintomaRepository.save(sintomaExiste));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!sintomaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sintomaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

