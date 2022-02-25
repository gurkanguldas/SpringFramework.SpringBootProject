package com.gurkanguldas.personsevice.business.service.impl;

import com.gurkanguldas.personsevice.exception.EntityNotFoundException;
import com.gurkanguldas.personsevice.business.dto.PersonDto;
import com.gurkanguldas.personsevice.data.Person;
import com.gurkanguldas.personsevice.data.repository.IPersonRepository;
import com.gurkanguldas.personsevice.business.service.CrudBaseService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class PersonService extends CrudBaseService<Person,PersonDto,Long>{

    @Autowired
    IPersonRepository repository;

    public PersonService(IPersonRepository repository)
    {
        super(repository);
    }

    // Find by person idetity
    public PersonDto findByPersonIdentificationNumber(Long id)
    {
        Optional<PersonDto> optional = Optional.ofNullable(entitytoDto(repository.findByPersonIdentificationNumber(id)));
        return optional.orElseThrow(() -> new EntityNotFoundException("Person"));
    }
}
