package com.example.cp01.Entidade.Dtos;

import java.util.Date;

public record SintomaDTO(Long pacienteId, Date data, String descricao, String gravidade) {
}
