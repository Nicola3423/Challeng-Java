package com.example.cp01.Entidade.Dtos;

import java.util.Date;

public record FeedbackDTO(
        Long pacienteId,
        Date data,
        String comentario,
        Integer nota) {
}
