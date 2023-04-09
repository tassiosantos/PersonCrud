package com.api.personcrud.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.api.personcrud.models.Endereco;
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
        Pessoa pessoa = new Pessoa();
        if(newPessoa.getMainAdress() == null){
            pessoa = this.pessoaRespository.save(newPessoa);
        }else{
            Endereco endereco = newPessoa.getMainAdress();

            newPessoa.setMainAdress(null);
            pessoa = this.pessoaRespository.save(newPessoa);

            endereco.setPessoa(pessoa);
            endereco = this.enderecoRepository.save(endereco);
            
            pessoa.setMainAdress(endereco);
            pessoa = this.pessoaRespository.save(pessoa);
        }
        
        return pessoa;

        
    }



    public boolean exists(Long pessoaId) {

        return this.pessoaRespository.existsById(pessoaId);
    }


    @Transactional
    public Pessoa changePersonMainAdress(Endereco mainAdress, Long pessoaId) {
        Pessoa pessoa = this.pessoaRespository.getReferenceById(pessoaId);
        if(mainAdress.getId() != null){
            if(mainAdress.getPessoa() != null){
                if(mainAdress.getPessoa().getId().equals(pessoaId)){
                    pessoa.setMainAdress(mainAdress);
                    this.pessoaRespository.save(pessoa);
                }else{
                    pessoa.setMainAdress(null);

                }
            }else{
                mainAdress.setPessoa(pessoa);
                mainAdress = this.enderecoRepository.save(mainAdress);
                pessoa.setMainAdress(mainAdress);
                this.pessoaRespository.save(pessoa);
            }
        }else{
            mainAdress.setPessoa(pessoa);
            mainAdress = this.enderecoRepository.save(mainAdress);
            pessoa.setMainAdress(mainAdress);
            pessoa = this.pessoaRespository.save(pessoa);     
        }
       
        return pessoa;
    }
    
    


}