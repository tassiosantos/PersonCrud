package com.api.personcrud.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.personcrud.Dtos.EnderecoDto;
import com.api.personcrud.models.Endereco;
import com.api.personcrud.models.Pessoa;
import com.api.personcrud.services.EnderecoService;

@RestController()
@CrossOrigin
@RequestMapping(path = "/endereco")
public class EnderecoController {
    private final EnderecoService enderecoService;


    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping(path = "/new/{pessoaId}")
    public ResponseEntity<Endereco> createEndereco(@RequestBody @Valid Endereco endereco, @PathVariable("pessoaId") Long pessoaId){
        ResponseEntity<Endereco> responseEntity = new ResponseEntity<Endereco>(endereco, HttpStatus.CONTINUE);
        try {
            Endereco novoEndereco = this.enderecoService.createEndereco(endereco, pessoaId);
            if(novoEndereco != null){
                responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
            }else{
                responseEntity = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
            }
            
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        return responseEntity;
        
    }


    @GetMapping(path = "/getAll/{pessoaId}")
    public ResponseEntity<List<EnderecoDto>> getAll(@PathVariable("pessoaId") Long pessoaId){
        ResponseEntity<List<EnderecoDto>> responseEntity = ResponseEntity.status(HttpStatus.CONTINUE).body(null);
        try {
            List<EnderecoDto> enderecosDto =  new ArrayList<EnderecoDto>();
            List<Endereco> enderecos = this.enderecoService.getAll(pessoaId);
            if(enderecos != null){
                
                for(Endereco endereco: enderecos){
                    EnderecoDto enderecoDto = new EnderecoDto();
                    enderecoDto.setCep(endereco.getCep());
                    enderecoDto.setCidade(endereco.getCidade());
                    enderecoDto.setId(endereco.getId());
                    enderecoDto.setLogradouro(endereco.getLogradouro());
                    enderecoDto.setNumero(endereco.getNumero());
                    
                    enderecosDto.add(enderecoDto);

                }
                responseEntity = ResponseEntity.status(HttpStatus.OK).body(enderecosDto);
            }else{
                responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseEntity;

    }

}
