// package com.api.personcrud.controllers;

// import java.util.ArrayList;
// import java.util.List;

// import javax.validation.Valid;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;


// import com.api.personcrud.models.Endereco;
// import com.api.personcrud.services.EnderecoService;

// @RestController()
// @CrossOrigin
// @RequestMapping(path = "/endereco")
// public class EnderecoController {
//     private final EnderecoService enderecoService;


//     public EnderecoController(EnderecoService enderecoService) {
//         this.enderecoService = enderecoService;
//     }

//     @PostMapping(path = "/new/{pessoaId}")
//     public ResponseEntity<Endereco> createEndereco(@RequestBody @Valid Endereco endereco, @PathVariable("pessoaId") Long pessoaId){
//         ResponseEntity<Endereco> responseEntity = new ResponseEntity<Endereco>(endereco, HttpStatus.CONTINUE);
//         try {
//             Endereco novoEndereco = this.enderecoService.createEndereco(endereco, pessoaId);
//             if(novoEndereco != null){
//                 responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
//             }else{
//                 responseEntity = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
//             }
            
        
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
        

//         return responseEntity;
        
//     }


//     @GetMapping(path = "/getAll/{pessoaId}")
//     public ResponseEntity<List<Endereco>> getAll(@PathVariable("pessoaId") Long pessoaId){
//         ResponseEntity<List<Endereco>> responseEntity = ResponseEntity.status(HttpStatus.CONTINUE).body(null);
//         try {
//             List<Endereco> enderecos = this.enderecoService.getAll(pessoaId);
//             if(enderecos != null){
                
//                  responseEntity = ResponseEntity.status(HttpStatus.OK).body(enderecos);
//             }else{
//                 responseEntity = ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
//             }
        
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//         return responseEntity;

//     }

// }
