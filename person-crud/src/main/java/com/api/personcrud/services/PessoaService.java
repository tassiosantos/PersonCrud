package com.api.personcrud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.personcrud.Dtos.EnderecoDto;
import com.api.personcrud.Dtos.PessoaDto;
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
            endereco = this.enderecoRepository.save(endereco);
            pessoa.setMainAdress(endereco);
            pessoa = this.pessoaRespository.save(pessoa);
        }
        
        
        
        return pessoa;

        
    }



    public boolean exists(Long pessoaId) {

        return this.pessoaRespository.existsById(pessoaId);
    }



    public PessoaDto changePersonMainAdress(Endereco mainAdress, Long pessoaId) {
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
            System.out.println(mainAdress.getId());
            pessoa = this.pessoaRespository.save(pessoa);     
        }
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNmPessoaDto(pessoa.getNmPessoa());
        pessoaDto.setDtNascimento(pessoa.getDtNascimento());
        pessoaDto.setId(pessoa.getId());

        EnderecoDto enderecoDto = new EnderecoDto();
        enderecoDto.setCep(mainAdress.getCep());
        enderecoDto.setCidade(mainAdress.getCidade());
        enderecoDto.setId(mainAdress.getId());
        enderecoDto.setLogradouro(mainAdress.getLogradouro());
        enderecoDto.setNumero(mainAdress.getNumero());

        pessoaDto.setMainAdress(enderecoDto);
        
        return pessoaDto;
    }
    
    


}