package com.example.ejMongoTemplate.infrastructure.repository;

import com.example.ejMongoTemplate.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PersonaRepositorio extends MongoRepository<Persona, Integer> {
    @Query("select p from Persona p where p.usuario = ?1")
    Persona findByUsuario(String usuario);


//    public List<Persona> getData(PersonSearch conditions, int pageSize, int pageNumber);


}
