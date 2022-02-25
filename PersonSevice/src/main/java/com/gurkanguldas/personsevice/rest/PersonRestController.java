package com.gurkanguldas.personsevice.rest;

import com.gurkanguldas.personsevice.business.dto.PersonDto;
import com.gurkanguldas.personsevice.business.service.impl.PersonService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/rest/person")
public class PersonRestController
{
    @Autowired
    PersonService personService;

    //http://localhost:8082/rest/person/get/people
    @GetMapping("/get/people")
    public ResponseEntity<?>  getPeople()
    {
       return ResponseEntity.ok().body(personService.findAll());
    }

    //http://localhost:8082/rest/person/get?id=1
    @GetMapping("/get")
    public ResponseEntity<?> getPerson(@RequestParam("id")Long personId)
    {
        return ResponseEntity.ok().body(personService.find(personId));
    }

    //http://localhost:8082/rest/person/get/identity?id=12345612345
    @GetMapping("/get/identity")
    public ResponseEntity<?> getPersonIdentification(@RequestParam("id")Long personIdentity)
    {
        return ResponseEntity.ok().body(personService.findByPersonIdentificationNumber(personIdentity));
    }

    //http://localhost:8082/rest/person/save
    @PostMapping(value = "/save")
    public ResponseEntity<?> getPersonSave(@Valid @RequestBody PersonDto personDto, BindingResult bindingResult)
    {
        personService.save(personDto,bindingResult);
        return ResponseEntity.ok().build();
    }

    //http://localhost:8082/rest/person/delete?id=1
    @DeleteMapping (value = "/delete")
    public ResponseEntity<?> getPersonDelete(@RequestParam("id")Long personId)
    {
       personService.delete(personId);
       return ResponseEntity.ok().build();
    }
}