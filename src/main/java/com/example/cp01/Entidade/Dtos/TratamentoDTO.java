package com.example.cp01.Entidade.Dtos;

import java.util.Date;

public record TratamentoDTO(String descricao, Long pacienteId, String tipo, Date data) {
}
