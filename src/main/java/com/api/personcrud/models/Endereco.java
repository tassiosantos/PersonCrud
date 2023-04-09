package com.api.personcrud.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "enderecos")
public class Endereco implements Serializable {
    private static final long serialVersionUID = 1L;
       
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Pessoa.class)
    @JoinColumn(name = "pessoa_id", nullable = false)
    @JsonIgnore
    private Pessoa pessoa;

    @Column(nullable = false)
    private String logradouro;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private String numero;
    @Column(nullable = false)
    private String cidade;

    public Endereco() {
    }

    public Endereco(Pessoa pessoa, String logradouro, String cep, String numero, String cidade) {
        this.pessoa = pessoa;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
    }
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Pessoa getPessoa() {
        return this.pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    public String getLogradouro() {
        return this.logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }
    public String getCep() {
        return this.cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getNumero() {
        return this.numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getCidade() {
        return this.cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Endereco [id=" + this.id + ", cdPessoa=" + this.pessoa.getId() + ", logradouro=" + this.logradouro + ", cep=" + this.cep
                + ", numero=" + this.numero + ", cidade=" + this.cidade + "]";
    }



    
}
