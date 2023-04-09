package com.api.personcrud.controllers;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.personcrud.models.Endereco;
import com.api.personcrud.models.Pessoa;
import com.api.personcrud.services.EnderecoService;
import com.api.personcrud.services.PessoaService;



@RestController()
@CrossOrigin
@RequestMapping(path = "/pessoa")
public class PessoaController {
    
    private final PessoaService pessoaService;
    private final EnderecoService enderecoService;


    public PessoaController(PessoaService pessoaService, EnderecoService enderecoService){
        this.pessoaService = pessoaService;
        this.enderecoService = enderecoService;
    }
    
    @PostMapping(path = "/new")
	public ResponseEntity<Pessoa> createPessoa(@RequestBody @Valid Pessoa newPessoa){
	    return ResponseEntity.status(HttpStatus.CREATED).body(this.pessoaService.createPessoa(newPessoa));
	}


    @PostMapping(path = "/newAdress/{pessoaId}")
    public ResponseEntity<Endereco> createEndereco(@RequestBody @Valid Endereco endereco, @PathVariable("pessoaId") Long pessoaId){
        ResponseEntity<Endereco> responseEntity = new ResponseEntity<Endereco>(endereco, HttpStatus.CONTINUE);
        try {
            Endereco novoEndereco = this.enderecoService.createEndereco(endereco, pessoaId);
            Pessoa pessoa = this.pessoaService.getPessoaById(pessoaId);
            if(novoEndereco != null){
                responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
                if(pessoa.getMainAdress() == null){
                    this.pessoaService.changePersonMainAdress(novoEndereco, pessoaId);
                }

            }else{
                responseEntity = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
            }
            
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        return responseEntity;
        
    }


    @GetMapping(path = "/getAll")
    public List<Pessoa> getAll(){
        return this.pessoaService.getAll();
    }

    @GetMapping(path = "/{pessoaId}")
    public Pessoa getPessoaById(@PathVariable("pessoaId") @Valid Long pessoaId){

        return this.pessoaService.getPessoaById(pessoaId);
    }

    @GetMapping(path = "/mainAdress/{pessoaId}")
    public Endereco getMainAdress(@PathVariable("pessoaId") Long pessoaId){
        return this.pessoaService.getPessoaById(pessoaId).getMainAdress();
    }

    
    @GetMapping(path = "/getAdresses/{pessoaId}")
    public List<Endereco> getAdresses(@PathVariable("pessoaId") Long pessoaId){
        return this.enderecoService.getAll(pessoaId);
    }





    
    @PutMapping(path = "/{pessoaId}")
	public ResponseEntity<Pessoa> updatePessoa(@RequestBody @Valid Pessoa changedPessoa, @PathVariable("pessoaId") Long pessoaId){
        ResponseEntity<Pessoa> responseEntity = new ResponseEntity<Pessoa>(new Pessoa(), HttpStatus.CONTINUE);
        Pessoa pessoa = new Pessoa();

        if(changedPessoa.getId() == null){
            pessoa = this.pessoaService.getPessoaById(pessoaId);
            ModelMapper modelMapper = new ModelMapper();
            modelMapper.map(changedPessoa, pessoa);
            this.pessoaService.updatePessoa(pessoa);
            ResponseEntity.status(HttpStatus.OK).body(pessoa);
        }else{
            if(changedPessoa.getId() == pessoaId){
                pessoa = this.pessoaService.updatePessoa(changedPessoa);
                responseEntity = ResponseEntity.status(HttpStatus.OK).body(pessoa);
            }else{
                responseEntity = ResponseEntity.status(HttpStatus.CONFLICT).body(null);
            }
            
        }
	    
    
	    return responseEntity;
	}

    @PutMapping(path = "/setMainAdress/{pessoaId}/{enderecoId}")
	public ResponseEntity<Pessoa> changePersonMainAdress(@PathVariable("pessoaId") @Valid Long pessoaId, @PathVariable("enderecoId") @Valid Long enderecoId){

        ResponseEntity<Pessoa> responseEntity = new ResponseEntity<Pessoa>(new Pessoa(), HttpStatus.CONTINUE);
            Endereco mainAdress = this.enderecoService.getEnderecoById(enderecoId);
            Pessoa pessoa = this.pessoaService.getPessoaById(pessoaId);
            

            if(pessoa != null){
                pessoa.setMainAdress(mainAdress);
                this.pessoaService.updatePessoa(pessoa);
                responseEntity = ResponseEntity.status(HttpStatus.OK).body(pessoa);
            }else{
               responseEntity = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
            }

        return responseEntity;


	}

}
