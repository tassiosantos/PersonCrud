package com.api.personcrud.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.personcrud.models.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    
    Pessoa findById(long id);

    void delete(Pessoa pessoa);
        
    Pessoa save(Pessoa pessoa);

    
    List<Pessoa> findAll();
    

    



}
