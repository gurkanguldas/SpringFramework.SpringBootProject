package com.gurkanguldas.personsevice.service;

import com.gurkanguldas.personsevice.business.dto.PersonDto;
import com.gurkanguldas.personsevice.business.service.impl.PersonService;
import com.gurkanguldas.personsevice.exception.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

@SpringBootTest
@Order(value = 2)
public class PersonServiceTest
{
    @Autowired
    private PersonService service;

    @Mock
    private BindingResult bindingResult;

    @Test
    void FindPersonIdentificationOperation_Success()
    {
        MockitoAnnotations.openMocks(this);
        PersonDto personDto = PersonDto.builder()
                .personId(0L)
                .personName("Gurkan")
                .personSurname("Guldas")
                .personIdentificationNumber(12345612345L)
                .personMonthlyIncome(5000L)
                .personPhoneNumber(5443443434L)
                .personCredicScore(1000l)
                .build();
        service.save(personDto,bindingResult);

        PersonDto person = service.findByPersonIdentificationNumber(12345612345L);
        Assertions.assertEquals(personDto.getPersonName(), person.getPersonName());

        service.delete(person.getPersonId());
    }
    @Test
    void FindPersonIdentificationOperation_EntityIsNull_Throw_EntityNotFoundException()
    {
        boolean isException = false;

        try
        {
            service.findByPersonIdentificationNumber(0L);
        }
        catch (EntityNotFoundException e)
        {
            isException = true;
        }

        Assertions.assertTrue(isException);
    }

}
