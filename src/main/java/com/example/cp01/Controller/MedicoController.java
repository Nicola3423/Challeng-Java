package com.example.cp01.Controller;


import com.example.cp01.Entidade.Dtos.MedicoDTO;
import com.example.cp01.Entidade.Medico;
import com.example.cp01.Repository.MedicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<Medico> create(@RequestBody Medico medico) {
        return new ResponseEntity<>(medicoRepository.save(medico), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medico> get(@PathVariable Long id) {
        return medicoRepository.findById(id)
                .map(medico -> ResponseEntity.ok(medico))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Medico>> list(Pageable pageable) {
        return ResponseEntity.ok(medicoRepository.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medico> update(@PathVariable Long id, @Valid @RequestBody MedicoDTO medicoDTO) {
        Optional<Medico> optionalMedico = medicoRepository.findById(id);
        if (optionalMedico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Medico medicoExiste = optionalMedico.get();
        medicoExiste.setPacienteId(medicoDTO.pacienteId());
        medicoExiste.setNome(medicoDTO.nome());
        medicoExiste.setEmail(medicoDTO.email());
        medicoExiste.setTelefone(medicoDTO.telefone());
        medicoExiste.setCrm(medicoDTO.crm());

        return ResponseEntity.ok(medicoRepository.save(medicoExiste));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!medicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        medicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
