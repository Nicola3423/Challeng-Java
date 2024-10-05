package com.example.cp01.Entidade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Entity
@Validated
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Long pacienteId;
    @NotNull
    private String nome;
    @NotNull
    private String telefone;
    @NotNull
    private String email;
    @NotNull
    private String crm;

    public Medico(Long id, Long pacienteId, String nome, String telefone, String email, String crm) {
        this.id = id;
        this.pacienteId = pacienteId;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.crm = crm;
    }


    public Medico() {}



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }


}
