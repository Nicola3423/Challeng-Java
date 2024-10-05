package com.example.cp01.Entidade.Dtos;

import java.util.Date;

public record PacienteDTO(Long pacienteId, String nome, String email, Integer telefone, Date dataNascimento) {
}
