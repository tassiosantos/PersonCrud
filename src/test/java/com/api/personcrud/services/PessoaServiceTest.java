package com.api.personcrud.services;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.personcrud.models.Endereco;
import com.api.personcrud.models.Pessoa;
import com.api.personcrud.repositories.EnderecoRepository;
import com.api.personcrud.repositories.PessoaRepository;


@SpringBootTest
@DisplayName("PessoaServiceTest")
public class PessoaServiceTest{
    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    private Pessoa pessoa1;
    private Pessoa pessoa2;

    private Endereco endereco1;
    private Endereco endereco2;
    
    
    @BeforeEach
    void classesTeste() {
        pessoa1 = new Pessoa("João", LocalDate.of(2001,07,03));
        pessoa2 = new Pessoa("Maria", LocalDate.of(2003,12,03));

        endereco1 = new Endereco(pessoa1, "Rua A", "45075025", "025", "Salvador");
        endereco2 = new Endereco(pessoa2, "Rua B", "45028110", "110", "Vca");

        pessoa1.setMainAdress(endereco1);
        pessoa2.setMainAdress(endereco2);

        this.pessoaService.createPessoa(pessoa1);
        this.pessoaService.createPessoa(pessoa2);
    }



    @Test
    @DisplayName("Deve adicionar Pessoa")
    public void createPessoa() {

        Pessoa pessoa = new Pessoa("José", LocalDate.of(2011,12,03));
        Assertions.assertEquals("José", pessoa.getNmPessoa());

    }


    @Test
    @DisplayName("Deve atualizar Pessoa")
    public void updatePessoa() {
            
        Pessoa pessoa = new Pessoa("Jonas", LocalDate.of(2011,12,03));
        pessoa.setNmPessoa("Pedro");
        Assertions.assertEquals("Pedro", pessoa.getNmPessoa());
        
    }


    @Test
    @DisplayName("Deve buscar uma pessoa")
    void buscaPessoa() {
        Pessoa pessoa = this.pessoaService.getPessoaById(pessoa1.getId());

        Assertions.assertNotNull(pessoa);
        Assertions.assertEquals(pessoa1.toString(), pessoa.toString());
    }


    @Test
    @DisplayName("Deve buscar todas as pessoas")
    void buscaPessoas() {
        List<Pessoa> pessoas = pessoaService.getAll();

        
        Assertions.assertNotNull(pessoas);
        Assertions.assertEquals(2, pessoas.size());
    }


    
}
