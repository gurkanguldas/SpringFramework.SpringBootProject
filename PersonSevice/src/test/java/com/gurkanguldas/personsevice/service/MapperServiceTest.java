package com.gurkanguldas.personsevice.service;

import com.gurkanguldas.personsevice.business.dto.PersonDto;
import com.gurkanguldas.personsevice.business.service.MapperService;
import com.gurkanguldas.personsevice.data.Person;
import com.gurkanguldas.personsevice.exception.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MapperServiceTest
{
    @Autowired
    MapperService<Person, PersonDto> service;

    @Test
    void EntityDto_Mapper_To_Entity_Success()
    {
        PersonDto personDto = PersonDto.builder()
                .personId(1L)
                .personName("Gurkan")
                .personSurname("Guldas")
                .personIdentificationNumber(12345612345L)
                .personMonthlyIncome(5000L)
                .personPhoneNumber(5443443434L)
                .build();

        Person person = service.dtoToEntity(personDto);
        Assertions.assertEquals(person.getPersonName(),personDto.getPersonName());
    }
    @Test
    void EntityDto_Null_Mapper_To_Entity_Throw_EntityNotFoundException()
    {
        boolean isException = false;
        try
        {
            service.dtoToEntity(null);
        }
        catch (EntityNotFoundException e)
        {
            isException = true;
        }
        Assertions.assertTrue(isException);
    }
    @Test
    void Entity_Mapper_To_EntityDto_Success()
    {
        Person person = Person.builder()
                .personId(1L)
                .personName("Gurkan")
                .personSurname("Guldas")
                .personIdentificationNumber(12345612345L)
                .personMonthlyIncome(5000L)
                .personPhoneNumber(5443443434L)
                .build();

        PersonDto personDto = service.entitytoDto(person);
        Assertions.assertEquals(personDto.getPersonName(),person.getPersonName());
    }
    @Test
    void Entity_Null_Mapper_To_EntityDto_Throw_EntityNotFoundException()
    {
        boolean isException = false;
        try
        {
            PersonDto person = service.entitytoDto(null);
        }
        catch (EntityNotFoundException e)
        {
            isException = true;
        }
        Assertions.assertTrue(isException);
    }
    @Test
    void EntityClass_To_String()
    {
        String strEntity = service.entityClassToString();
        Assertions.assertEquals(strEntity,"Person");
    }
}
