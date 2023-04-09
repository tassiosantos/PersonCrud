package com.api.personcrud.models;

import java.io.Serializable;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@Entity
@Table(name = "pessoas")
public class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "pessoa_id", unique = true)
    private Long id;
    
    @Column(nullable = false, name = "pessoa_nome")
    private String nmPessoa;

    @DateTimeFormat(pattern = "YYYY-MM-DD")
    @Column(nullable = false, name = "data_nascimento")
    private LocalDate dtNascimento;
    
    @OneToOne
    @JoinColumn(nullable = true, name = "main_adress_id", referencedColumnName = "id")
    private Endereco mainAdress;

    public Pessoa() {
    }

    public Pessoa(String nmPessoa, LocalDate dtNascimento, Endereco mainAdress) {
        this.nmPessoa = nmPessoa;
        this.dtNascimento = dtNascimento;
        this.mainAdress = mainAdress;
    }

    public Pessoa(String nmPessoa) {
        this.nmPessoa = nmPessoa;
    }



    public Pessoa(String nmPessoa, LocalDate dtNascimento) {
        this.nmPessoa = nmPessoa;
        this.dtNascimento = dtNascimento;
    }


    public Long getId() {
        return this.id;
    }

    public String getNmPessoa() {
        return this.nmPessoa;
    }

    public void setNmPessoa(String nmPessoa) {
        this.nmPessoa = nmPessoa;
    }

    public LocalDate getDtNascimento() {
        return this.dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Endereco getMainAdress() {
        return this.mainAdress;
    }

    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    public void setMainAdress(Endereco mainAdress) {
        this.mainAdress = mainAdress;
    }

    
    @Override
    public String toString() {
        return "Pessoa [id=" + this.id + ", nmPessoa=" + this.nmPessoa + ", dtNascimento=" + this.dtNascimento + ", mainAdress="
                + this.mainAdress + "]";
    }

}
