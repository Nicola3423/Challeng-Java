package com.example.cp01.Controller;

import com.example.cp01.Entidade.Dtos.TratamentoDTO;
import com.example.cp01.Entidade.Tratamento;
import com.example.cp01.Repository.TratamentoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tratamento")
public class TratamentoController {

    @Autowired
    TratamentoRepository tratamentoRepository;

    @PostMapping
    public ResponseEntity<Tratamento> create(@RequestBody Tratamento tratamento) {
        return new ResponseEntity<>(tratamentoRepository.save(tratamento), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tratamento> get(@PathVariable Long id) {
        return tratamentoRepository.findById(id)
                .map(tratamento -> ResponseEntity.ok(tratamento))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<Tratamento>> list(Pageable pageable) {
        return ResponseEntity.ok(tratamentoRepository.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tratamento> update(@PathVariable Long id, @Valid @RequestBody TratamentoDTO tratamentoDTO) {
        Optional<Tratamento> optionalTratamento = tratamentoRepository.findById(id);
        if (optionalTratamento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Tratamento tratamentoExiste = optionalTratamento.get();
        tratamentoExiste.setPacienteId(tratamentoDTO.pacienteId());
        tratamentoExiste.setData(tratamentoDTO.data());
        tratamentoExiste.setDescricao(tratamentoDTO.descricao());
        tratamentoExiste.setTipo(tratamentoDTO.tipo());

        return ResponseEntity.ok(tratamentoRepository.save(tratamentoExiste));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!tratamentoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        tratamentoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

