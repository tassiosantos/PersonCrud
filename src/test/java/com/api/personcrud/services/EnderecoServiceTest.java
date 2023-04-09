package com.api.personcrud.services;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.api.personcrud.models.Endereco;
import com.api.personcrud.models.Pessoa;
import com.api.personcrud.repositories.EnderecoRepository;
import com.api.personcrud.repositories.PessoaRepository;

@SpringBootTest
@DisplayName("EnderecoServiceTest")
public class EnderecoServiceTest {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PessoaService pessoaService;

    private Pessoa pessoa1;
    private Pessoa pessoa2;

    private Endereco endereco1;
    private Endereco endereco2;
    private Endereco endereco3;
    

    @BeforeEach
    void objetosTeste() {
        pessoa1 = new Pessoa("João", LocalDate.of(2001,07,03));
        pessoa2 = new Pessoa("Maria", LocalDate.of(2003,12,03));

        endereco1 = new Endereco(pessoa1, "Rua A", "45075025", "025", "Salvador");
        endereco2 = new Endereco(pessoa1, "Rua C", "45075023", "023", "Salvador");
        endereco3 = new Endereco(pessoa2, "Rua B", "45028110", "110", "Vca");

        pessoa1.setMainAdress(endereco1);
        pessoa2.setMainAdress(endereco2);

        this.pessoaService.createPessoa(pessoa1);
        this.pessoaService.createPessoa(pessoa2);

        this.enderecoService.createEndereco(endereco1, pessoa1.getId());
        this.enderecoService.createEndereco(endereco2, pessoa1.getId());
        this.enderecoService.createEndereco(endereco3, pessoa2.getId());

    }


    @Test
    @DisplayName("Deve ciar um endereço")
    void createEndereco() {
        Endereco endereco = new Endereco(pessoa2, "Rua B", "45028110", "110", "Vca");
        endereco = enderecoService.createEndereco(endereco, 1L);
        Assertions.assertNotNull(endereco.getId());
        Assertions.assertEquals("Rua B", endereco.getLogradouro());
    }



    @Test
    @DisplayName("Deve retornar endereço ou nulo")
    void buscaEndereco() {
        
        Endereco endereco = new Endereco();
        Assertions.assertNull(endereco.getId());

        endereco = enderecoService.getEnderecoById(endereco1.getId());
        Assertions.assertNotNull(endereco);
        
    }

    

    @Test
    @DisplayName("Deve retornar endereços de uma pessoa")
    void buscaEnderecos() {
        Pessoa pessoa = this.pessoaService.getPessoaById(pessoa1.getId());
        List<Endereco> enderecos = enderecoService.getAll(pessoa.getId());

        Assertions.assertNotNull(enderecos);
        Assertions.assertEquals(2, enderecos.size());
    }

}
