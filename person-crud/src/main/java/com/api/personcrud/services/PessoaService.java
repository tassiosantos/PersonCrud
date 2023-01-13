package com.api.personcrud.services;

import java.util.List;

import javax.transaction.TransactionScoped;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.personcrud.models.Pessoa;
import com.api.personcrud.repositories.EnderecoRepository;
import com.api.personcrud.repositories.PessoaRepository;



@Service
public class PessoaService{

    private final PessoaRepository pessoaRespository;
    private final EnderecoRepository enderecoRepository;

    

    public PessoaService(PessoaRepository pessoaRepository, EnderecoRepository enderecoRepository){
        this.pessoaRespository = pessoaRepository;
        this.enderecoRepository = enderecoRepository;
    }

    

	public Pessoa getPessoaById(long pessoaId) {
        return this.pessoaRespository.findById(pessoaId);
    }

    
    public List<Pessoa> getAll(){
        return this.pessoaRespository.findAll();
    }


    @Transactional
    public Pessoa updatePessoa(Pessoa changedPessoa) {
        return this.pessoaRespository.save(changedPessoa);
    }


    @Transactional
    public Pessoa createPessoa(Pessoa newPessoa) {
        return this.pessoaRespository.save(newPessoa);

        
    }



    public boolean exists(Long pessoaId) {

        return this.pessoaRespository.existsById(pessoaId);
    }
    
    


}