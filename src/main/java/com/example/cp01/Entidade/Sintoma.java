package com.example.cp01.Entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Entity
@Validated
public class Sintoma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long PacienteId;
    @NotNull
    private Date data;
    @NotNull
    private String descricao;
    @NotNull
    private String Gravidade;

    public Sintoma(String descricao, String gravidade, Date data, Long pacienteId, Long id) {
        this.descricao = descricao;
        Gravidade = gravidade;
        this.data = data;
        PacienteId = pacienteId;
        this.id = id;
    }

    public Sintoma() {}


    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getGravidade() {
        return Gravidade;
    }

    public void setGravidade(String gravidade) {
        Gravidade = gravidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacienteId() {
        return PacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        PacienteId = pacienteId;
    }

}
