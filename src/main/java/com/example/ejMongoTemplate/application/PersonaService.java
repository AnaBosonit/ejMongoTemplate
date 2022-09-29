package com.example.ejMongoTemplate.application;

import com.example.ejMongoTemplate.domain.Persona;
import com.example.ejMongoTemplate.infrastructure.dto.PersonaInputDTO;
import com.example.ejMongoTemplate.infrastructure.dto.PersonaOutputDTO;

import java.util.List;

public interface PersonaService {
    String getNombre();
    String getPoblacion();
    //int getEdad();

    void setNombre(String nombre);
    void setPoblacion(String poblacion);
    //void setEdad(int edad);

    int getId();


    public Persona addPersona(Persona persona);
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws Exception;


    public void deletePersona(int id) throws Exception;

    public PersonaOutputDTO getById(int id) throws Exception;

    public PersonaOutputDTO getByUser(String user);

    public List<PersonaOutputDTO> getAll();

    public PersonaOutputDTO alterPersona(PersonaInputDTO personaInputDTO);

    List<Persona> getPersonasList();


    String toString();
}
