package com.example.ejMongoTemplate.application;

import com.example.ejMongoTemplate.domain.Persona;
import com.example.ejMongoTemplate.errors.NotFoundException;
import com.example.ejMongoTemplate.errors.UnprocessableException;
import com.example.ejMongoTemplate.infrastructure.dto.PersonaInputDTO;
import com.example.ejMongoTemplate.infrastructure.dto.PersonaOutputDTO;
import com.example.ejMongoTemplate.infrastructure.repository.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepositorio personaRepositorio;


    private final MongoTemplate mongoTemplate;

    @Autowired
    public PersonaServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    Persona persona = new Persona();

    List<Persona> personaList = new ArrayList<>();


    @Override
    public String getNombre() {
        return persona.getName();
    }

    @Override
    public String getPoblacion() {
        return persona.getCity();
    }

    /*
    @Override
    public int getEdad() {
        return persona.get();
    }*/

    @Override
    public int getId(){
        return persona.getId_persona();
    }


    @Override
    public List<Persona> getPersonasList() {
        return personaList;
    }

    @Override
    public Persona addPersona(Persona persona) {
        personaList.add(persona);
        return persona;
    }



    @Override
    public void setNombre(String nombre) {
        persona.setName(nombre);
    }

    @Override
    public void setPoblacion(String poblacion) {
        persona.setCity(poblacion);
    }
/*
    @Override
    public void setEdad(int edad) {
        persona.setEdad(edad);
    }*/




    @Override
    public PersonaOutputDTO addPersona(PersonaInputDTO personaInputDTO) throws UnprocessableException {
        int id = personaInputDTO.getId_persona();
        if(personaRepositorio.findById(id) == null){
            throw new UnprocessableException("ID no puede ser nulo");}

        String usuario = personaInputDTO.getUsuario();
        if (usuario==null) {throw new UnprocessableException("Usuario no puede ser nulo");}
        if (usuario.length() > 10 || usuario.length() < 6) {
            throw new UnprocessableException("Longitud de usuario no puede ser superior a 10 ni inferior a 6 caracteres");
        }
        if(personaInputDTO.getPassword()==null) {throw new UnprocessableException("Password no puede ser nula");}
        if (personaInputDTO.getName()==null) {throw new UnprocessableException("Nombre no puede ser nulo");}
        if (personaInputDTO.getCompany_email()==null || personaInputDTO.getPersonal_email()==null) {throw new UnprocessableException("Los emails no pueden ser nulos");}
        if (personaInputDTO.getCity()==null) {throw new UnprocessableException("Ciudad no puede ser nula");}
        if (personaInputDTO.getActive()==null) {throw new UnprocessableException("Active no puede ser nulo");}
        if (personaInputDTO.getCreated_date()==null) {throw new UnprocessableException("Created_date no puede ser nula");}
        Persona persona = new Persona(personaInputDTO);


        mongoTemplate.save(persona);
       PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(persona);


        return personaOutputDTO;

    }


    @Override
    public void deletePersona(int id) throws Exception {
        Persona p = personaRepositorio.findById(id).orElseThrow(()-> new NotFoundException("No hay persona con ese id"));



        try{
            personaRepositorio.deleteById(id);}
        catch(Exception e){
            throw new NotFoundException("No se ha podido encontrar ningún usuario con ese id. No se ha podido borrar.");
        }

    }

    /*Al buscar por ID la persona, si es estudiante, return datos de ‘estudiante’, ‘profesores’ y los estudios.
     Si la persona es profesor, return datos de entidad profesor, estudiantes a su cargo, y para cada estudiante los estudios*/
    @Override
    public PersonaOutputDTO getById(int id) throws NotFoundException {
        //personaRepositorio.findById(id).orElseThrow(()->new Exception("Persona no encontrada."));
        Persona p = personaRepositorio.findById(id).orElseThrow(()->new NotFoundException("No se ha encontrado esa persona con ese id."));

        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(p);

            return personaOutputDTO;
    }

    @Override
    public PersonaOutputDTO getByUser(String user) {
        PersonaOutputDTO personaOutputDTO = new PersonaOutputDTO(personaRepositorio.findByUsuario(user));
        System.out.println("Usuario es " + user);
        return personaOutputDTO;
    }

    @Override
    public List<PersonaOutputDTO> getAll() {
        PersonaOutputDTO personaOutputDTO;
        List<PersonaOutputDTO> personaOutputDTOList = new ArrayList<>();
        for(Persona p:  personaRepositorio.findAll()) {
            personaOutputDTO = new PersonaOutputDTO(p);
            personaOutputDTOList.add(personaOutputDTO);
        }
        return personaOutputDTOList;
    }

    @Override
    public PersonaOutputDTO alterPersona(PersonaInputDTO personaInputDTO) {
        int id = personaInputDTO.getId_persona();
        PersonaOutputDTO personaOutputDTO = null;

        if(personaRepositorio.findById(id) != null){
            Persona persona = personaRepositorio.findById(id).orElseThrow(()->new NotFoundException("No se ha encontrado esa persona con ese id."));
            if(personaInputDTO.getName() != null){
                persona.setName(personaInputDTO.getName());
            }
            if(personaInputDTO.getSurname() != null){
                persona.setSurname(personaInputDTO.getSurname());
            }
            if(personaInputDTO.getActive() != null){
                persona.setActive(personaInputDTO.getActive());
            }
            if(personaInputDTO.getImagen_url() != null){
                persona.setImagen_url(personaInputDTO.getImagen_url());
            }
            if(personaInputDTO.getCity() != null){
                persona.setCity(personaInputDTO.getCity());
            }
            if(personaInputDTO.getCompany_email() != null){
                persona.setCompany_email(personaInputDTO.getCompany_email());
            }
            if(personaInputDTO.getPersonal_email() != null){
                persona.setPersonal_email(personaInputDTO.getPersonal_email());
            }
            if(personaInputDTO.getCreated_date() != null){
                persona.setCreated_date(personaInputDTO.getCreated_date());
            }
            if(personaInputDTO.getTermination_date() != null){
                persona.setTermination_date(personaInputDTO.getTermination_date());
            }
            if(personaInputDTO.getUsuario() != null){
                persona.setUsuario(personaInputDTO.getUsuario());
            }
            if (personaInputDTO.getUsuario()==null) {throw new UnprocessableException("Usuario no puede ser nulo");}
            if (personaInputDTO.getUsuario().length() > 10 || personaInputDTO.getUsuario().length() < 6) {
                throw new UnprocessableException("Longitud de usuario no puede ser superior a 10 ni inferior a 6 caracteres");
            }
            if(personaInputDTO.getPassword()==null) {throw new UnprocessableException("Password no puede ser nula");}
            if (personaInputDTO.getName()==null) {throw new UnprocessableException("Nombre no puede ser nulo");}
            if (personaInputDTO.getCompany_email()==null || personaInputDTO.getPersonal_email()==null) {throw new UnprocessableException("Los emails no pueden ser nulos");}
            if (personaInputDTO.getCity()==null) {throw new UnprocessableException("Ciudad no puede ser nula");}
            if (personaInputDTO.getActive()==null) {throw new UnprocessableException("Active no puede ser nulo");}
            if (personaInputDTO.getCreated_date()==null) {throw new UnprocessableException("Created_date no puede ser nula");}
            mongoTemplate.save(persona);

            personaOutputDTO = new PersonaOutputDTO(persona);

        }
        return personaOutputDTO;
    }
}
