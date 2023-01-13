package com.api.personcrud.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.api.personcrud.models.Pessoa;
import com.api.personcrud.repositories.EnderecoRepository;
import com.api.personcrud.repositories.PessoaRepository;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("PessoaServiceTest")
public class PessoaServiceTest{


    @MockBean
    private PessoaRepository pessoaRepository;

    @MockBean
    private EnderecoRepository enderecoRepository;
    
    @Autowired
    private PessoaService pessoaService;

      

    @Test
    @DisplayName("Deve adicionar Pessoa")
    public void createPessoa() {


            
        Mockito.when(this.pessoaService.createPessoa(any(Pessoa.class))).thenReturn(mock(Pessoa.class));
        
        Pessoa pessoa = new Pessoa("Joao", Date.valueOf("2011-12-03"));
        System.out.println(pessoa.getNmPessoa() + " " + pessoa.getDtNascimento());
        
        assertEquals("Dherik", pessoa.getNmPessoa());

    }


    @Test
    @DisplayName("Deve adicionar Pessoa")
    public void updatePessoa() {
            
        Mockito.when(this.pessoaService.updatePessoa(any(Pessoa.class))).thenReturn(mock(Pessoa.class));
        
        Pessoa pessoa = new Pessoa("Jonas", Date.valueOf("2011-12-03"));
      
        assertEquals("Dherik", pessoa.getNmPessoa());

    }






    
}
