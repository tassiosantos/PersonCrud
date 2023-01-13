package com.api.personcrud.controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.api.personcrud.Dtos.PessoaDto;
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



    @GetMapping(path = "/getAll")
    public List<Pessoa> getAll(){
        return this.pessoaService.getAll();
    }

    @GetMapping(path = "/{pessoaId}")
    public Pessoa getPessoaById(@PathVariable("pessoaId") Long pessoaId){
        return this.pessoaService.getPessoaById(pessoaId);
    }

    @GetMapping(path = "/mainAdress/{pessoaId}")
    public Endereco getMainAdress(@PathVariable("pessoaId") Long pessoaId){
        return this.pessoaService.getPessoaById(pessoaId).getMainAdress();
    }
    
    @PutMapping(path = "/{pessoaId}")
	public Pessoa updatePessoa(@RequestBody Pessoa changedPessoa){
	    Pessoa pessoa = this.pessoaService.updatePessoa(changedPessoa);
    
	    return pessoa;
	}

    @PutMapping(path = "/changeMainAdress/{pessoaId}")
	public ResponseEntity<PessoaDto> changePersonMainAdress(@RequestBody Endereco mainAdress, @PathVariable("pessoaId") Long pessoaId){
        ResponseEntity<PessoaDto> responseEntity = new ResponseEntity<PessoaDto>(new PessoaDto(), HttpStatus.CONTINUE);
        try {
            PessoaDto pessoa = this.pessoaService.changePersonMainAdress(mainAdress, pessoaId);    
            if(pessoa != null){
                responseEntity = ResponseEntity.status(HttpStatus.OK).body(pessoa);
            }else{
                responseEntity = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



        return responseEntity;


	}

}
