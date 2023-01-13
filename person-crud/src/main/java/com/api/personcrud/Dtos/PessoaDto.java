package com.api.personcrud.Dtos;

import java.util.Date;



public class PessoaDto{
    
    private Long id;
    

    private String nmPessoaDto;


    private Date dtNascimento;
    

    private EnderecoDto mainAdress;

    public PessoaDto() {
    }

    public PessoaDto(String nmPessoaDto, Date dtNascimento, EnderecoDto mainAdress) {
        this.nmPessoaDto = nmPessoaDto;
        this.dtNascimento = dtNascimento;
        this.mainAdress = mainAdress;
    }

    public PessoaDto(String nmPessoaDto) {
        this.nmPessoaDto = nmPessoaDto;
    }



    public PessoaDto(String nmPessoaDto, Date dtNascimento) {
        this.nmPessoaDto = nmPessoaDto;
        this.dtNascimento = dtNascimento;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public String getNmPessoaDto() {
        return this.nmPessoaDto;
    }

    public void setNmPessoaDto(String nmPessoaDto) {
        this.nmPessoaDto = nmPessoaDto;
    }

    public Date getDtNascimento() {
        return this.dtNascimento;
    }

    public void setDtNascimento(Date date) {
        this.dtNascimento = date;
    }

    public EnderecoDto getMainAdress() {
        return this.mainAdress;
    }

    public void setMainAdress(EnderecoDto mainAdress) {
        this.mainAdress = mainAdress;
    }

    @Override
    public String toString() {
        return "PessoaDto [id=" + this.id + ", nmPessoaDto=" + this.nmPessoaDto + ", dtNascimento=" + this.dtNascimento + ", mainAdress="
                + this.mainAdress + "]";
    }

}

