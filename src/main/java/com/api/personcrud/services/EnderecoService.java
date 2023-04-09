package com.api.personcrud.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;


import com.api.personcrud.models.Endereco;
import com.api.personcrud.models.Pessoa;
import com.api.personcrud.repositories.EnderecoRepository;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final PessoaService pessoaService;

    public EnderecoService(EnderecoRepository enderecoRepository, PessoaService pessoaService){
        this.enderecoRepository = enderecoRepository;
        this.pessoaService = pessoaService;
    }



    public Endereco getEnderecoById(Long enderecoId){
        return this.enderecoRepository.getReferenceById(enderecoId);
    }

    public List<Endereco> getAll(Long id) {
        if(validarEndereco(id)){
            return this.enderecoRepository.findByPessoa(this.pessoaService.getPessoaById(id));
        }else{
            return null;
        }
        
    }

    @Transactional
    public Endereco createEndereco(Endereco endereco, Long pessoaId) {
        
        if(validarEndereco(pessoaId)){
            endereco.setPessoa(pessoaService.getPessoaById(pessoaId));
            return this.enderecoRepository.save(endereco);
        }else{
            return null;
        }
        
    }


    @Transactional
    public boolean validarEndereco(Long pessoaId) {
        boolean valido = false;
        if(this.pessoaService.exists(pessoaId)){
            valido = true;
        }

        return valido;
    }




}
