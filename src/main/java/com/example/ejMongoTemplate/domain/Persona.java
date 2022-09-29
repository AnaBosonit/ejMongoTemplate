package com.example.ejMongoTemplate.domain;

import com.example.ejMongoTemplate.infrastructure.dto.PersonaInputDTO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter

@Document(collection = "persona")
public class Persona {


    @Id
    private int id_persona;

    private String usuario;

    private String password;

    private String name;

    private String surname;

    private String company_email;

    private String personal_email;

    private String city;

    private Boolean active;

    private Date created_date;

    private String imagen_url;

    private Date termination_date;




    public Persona(PersonaInputDTO personaInputDTO){

        setId_persona(personaInputDTO.getId_persona());
        setUsuario(personaInputDTO.getUsuario());
        setPassword(personaInputDTO.getPassword());
        setName(personaInputDTO.getName());
        setSurname(personaInputDTO.getSurname());
        setCity(personaInputDTO.getCity());
        setCompany_email(personaInputDTO.getCompany_email());
        setPersonal_email(personaInputDTO.getPersonal_email());
        setActive(personaInputDTO.getActive());
        setCreated_date(personaInputDTO.getCreated_date());
        setImagen_url(personaInputDTO.getImagen_url());
        setTermination_date(personaInputDTO.getTermination_date());
    }


    public Persona() {

    }
}
