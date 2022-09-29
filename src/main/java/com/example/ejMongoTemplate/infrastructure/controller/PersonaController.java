package com.example.ejMongoTemplate.infrastructure.controller;

import com.example.ejMongoTemplate.application.PersonaService;
import com.example.ejMongoTemplate.errors.NotFoundException;
import com.example.ejMongoTemplate.infrastructure.dto.PersonaInputDTO;
import com.example.ejMongoTemplate.infrastructure.dto.PersonaOutputDTO;
import com.example.ejMongoTemplate.infrastructure.repository.PersonaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @Autowired
    PersonaRepositorio personaRepositorio;

    @PostMapping("addperson")
    public PersonaOutputDTO addPersona(@RequestBody PersonaInputDTO personaInputDTO) throws Exception {

        return personaService.addPersona(personaInputDTO);

    }


    /*update*/
    @PutMapping("alterPersona")
    public PersonaOutputDTO alterPersona(@RequestBody PersonaInputDTO personaInputDTO) throws Exception {
        try{
            return personaService.alterPersona(personaInputDTO);}
        catch(NoSuchElementException k){
            throw new NotFoundException("Persona con id " + personaInputDTO.getId_persona() + " no encontrada.");
        }

    }
    /*delete*/
    @DeleteMapping("deletePersona/{id}")
    public void deletePersona(@PathVariable int id) throws NotFoundException {
        try{
            personaService.deletePersona(id);}
        catch(NoSuchElementException k){
            throw new NotFoundException("Persona con id " + id + " no encontrada.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*read*/
    /*
     * Buscar por ID */
    @GetMapping("{id}")
    public PersonaOutputDTO getById(@PathVariable int id) throws Exception {
        try{
            return personaService.getById(id);}
        catch(NoSuchElementException k){
            throw new NotFoundException("Persona con id " + id + " no encontrada.");
        }
    }


    /*- Buscar por nombre de usuario (campo usuario)*/

    @GetMapping("getByUsuario/{user}")
    public PersonaOutputDTO getByUsuario(@PathVariable String user, @RequestParam(defaultValue="simple") String outputType){
        return personaService.getByUser(user);

    }

    /*- Mostrar todos los registros.*/
    @CrossOrigin(origins="*")
    @GetMapping("getall")
    public List<PersonaOutputDTO> getAll(@RequestParam(defaultValue="simple") String outputType){

        return personaService.getAll();
    }


}
