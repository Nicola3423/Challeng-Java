package com.example.cp01.Controller;

import com.example.cp01.Entidade.Dtos.PacienteDTO;
import com.example.cp01.Entidade.Paciente;
import com.example.cp01.Repository.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteRepository pacienteRepository;

    @PostMapping
    public ResponseEntity<Paciente> create(@RequestBody Paciente paciente) {
        return new ResponseEntity<>(pacienteRepository.save(paciente), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> get(@PathVariable Long id) {
        return pacienteRepository.findById(id)
                .map(paciente -> ResponseEntity.ok(paciente))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Paciente>> list(Pageable pageable) {
        return ResponseEntity.ok(pacienteRepository.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> update(@PathVariable Long id, @Valid @RequestBody PacienteDTO pacienteDTO) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
        if (optionalPaciente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Paciente pacienteExiste = optionalPaciente.get();
        pacienteExiste.setNome(pacienteDTO.nome());
        pacienteExiste.setEmail(pacienteDTO.email());
        pacienteExiste.setTelefone(pacienteDTO.telefone());

        return ResponseEntity.ok(pacienteRepository.save(pacienteExiste));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!pacienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        pacienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

