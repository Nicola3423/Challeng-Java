package com.example.cp01.Entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.sql.Clob;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Validated
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long pacienteId;
    @NotNull
    private Date data;
    @NotNull
    private String comentario;
    @NotNull
    private int nota;

    public Feedback(Long id, Date data, int nota, String comentario, Long pacienteId) {
        this.id = id;
        this.data = data;
        this.nota = nota;
        this.comentario = comentario;
        this.pacienteId = pacienteId;
    }
    public Feedback() {}
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

}
