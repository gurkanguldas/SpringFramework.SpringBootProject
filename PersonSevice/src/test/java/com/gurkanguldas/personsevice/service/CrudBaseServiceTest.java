package com.gurkanguldas.personsevice.service;

import com.gurkanguldas.personsevice.business.dto.PersonDto;
import com.gurkanguldas.personsevice.business.service.CrudBaseService;
import com.gurkanguldas.personsevice.data.Person;
import com.gurkanguldas.personsevice.exception.EntityNotFoundException;
import com.gurkanguldas.personsevice.exception.EntityValidateException;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Order(value = 1)
public class CrudBaseServiceTest
{
    @Autowired
    CrudBaseService<Person,PersonDto,Long> service;

    @Mock
    BindingResult bindingResult;

    @Test
    @Order(1)
    void CreateOperation_BindingResult_HasErrorFalse_Success()
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
        Assertions.assertNotNull(service.find(1L));
    }
    @Test
    @Order(2)
    void CreateOperation_BindingResult_HasErrorTrue_EntityValidateException()
    {
        boolean isException = false;
        MockitoAnnotations.openMocks(this);
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        PersonDto personDto = PersonDto.builder().build();

        try
        {
            service.save(personDto,bindingResult);
        }
        catch (EntityValidateException e)
        {
            isException = true;
        }

        Assertions.assertTrue(isException);
    }
    @Test
    @Order(3)
    void UpdateOperation_BindingResult_HasErrorFalse_Success()
    {
        PersonDto personDto = service.find(1L);
        personDto.setPersonMonthlyIncome(10000L);

        service.save(personDto,bindingResult);
        Assertions.assertEquals(personDto.getPersonMonthlyIncome(),service.find(personDto.getPersonId()).getPersonMonthlyIncome());
    }
    @Test
    @Order(5)
    void DeleteOperation_ExistEntityIsFalse_Success()
    {
        Long creditId = service.find(1L).getPersonId();
        service.delete(creditId);
        Assertions.assertFalse(service.isExistEntity(creditId));
    }
    @Test
    @Order(6)
    void FindOperation_EntityIsNull_Return_EntityNotFoundException()
    {
        boolean isException = false;
        try
        {
            service.find(0L);
        }
        catch (EntityNotFoundException e)
        {
            isException = true;
        }
        Assertions.assertTrue(isException);
    }
}
