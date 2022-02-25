package com.gurkanguldas.personsevice.data.repository;

import com.gurkanguldas.personsevice.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonRepository extends JpaRepository<Person,Long>
{
    Person findByPersonIdentificationNumber(Long id);
}
