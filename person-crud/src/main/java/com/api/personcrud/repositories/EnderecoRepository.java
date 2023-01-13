package com.api.personcrud.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.personcrud.models.Endereco;
import com.api.personcrud.models.Pessoa;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

    Endereco save(Endereco endereco);
    List<Endereco> findByPessoa(Pessoa pessoa);

}
