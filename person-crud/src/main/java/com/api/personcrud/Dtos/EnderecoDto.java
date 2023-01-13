package com.api.personcrud.Dtos;

public class EnderecoDto {
    
    private static final long serialVersionUID = 1L;
  
    
    private Long id;
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    
    public EnderecoDto() {
    }

    public EnderecoDto(String logradouro, String cep, String numero, String cidade) {
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
        return "EnderecoDto [id=" + this.id + ", cdPessoa=" + ", logradouro=" + this.logradouro + ", cep=" + this.cep
                + ", numero=" + this.numero + ", cidade=" + this.cidade + "]";
    }



    
}
