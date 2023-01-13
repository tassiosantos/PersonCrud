package com.api.personcrud.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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



    @GetMapping(path = "/getAll")
    public List<Pessoa> getAll(){
        return this.pessoaService.getAll();
    }

    @GetMapping(path = "/{pessoaId}")
    public Pessoa getPessoaById(@PathVariable("pessoaId") Long pessoaId){
        return this.pessoaService.getPessoaById(pessoaId);
    }

    
    @PutMapping(path = "/{pessoaId}")
	public Pessoa updatePessoa(@RequestBody Pessoa changedPessoa){
        System.out.println(changedPessoa);    
	    Pessoa pessoa = this.pessoaService.updatePessoa(changedPessoa);
    
	    return pessoa;
	}

}
