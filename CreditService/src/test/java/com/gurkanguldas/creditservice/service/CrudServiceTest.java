package com.gurkanguldas.creditservice.service;

import com.gurkanguldas.creditservice.business.dto.CreditDto;
import com.gurkanguldas.creditservice.business.service.CrudBaseService;
import com.gurkanguldas.creditservice.data.Credit;
import com.gurkanguldas.creditservice.exception.EntityNotFoundException;
import com.gurkanguldas.creditservice.exception.EntityValidateException;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CrudServiceTest
{
    @Autowired
    CrudBaseService<Credit,CreditDto,String> service;

    @Mock
    BindingResult bindingResult;

    @Test
    @Order(1)
    void CreateOperation_BindingResult_HasErrorFalse_Success()
    {
        MockitoAnnotations.openMocks(this);
        CreditDto creditDto = CreditDto.builder()
                .creditId("testid")
                .personIdentificationNumber(99693724104L)
                .creditLimit(50000l)
                .creditStatus(true)
                .build();
        service.save(creditDto,bindingResult);
        Assertions.assertNotNull(service.find("testid"));
    }
    @Test
    @Order(2)
    void CreateOperation_BindingResult_HasErrorTrue_EntityValidateException()
    {
        boolean isException = false;
        MockitoAnnotations.openMocks(this);
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        CreditDto creditDto = CreditDto.builder().build();

        try
        {
            service.save(creditDto,bindingResult);
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
        CreditDto creditDto = service.find("testid");
        creditDto.setCreditLimit(10000l);

        service.save(creditDto,bindingResult);
        Assertions.assertEquals(creditDto.getCreditLimit(),service.find(creditDto.getCreditId()).getCreditLimit());
    }

    @Test
    @Order(5)
    void DeleteOperation_ExistEntityIsFalse_Success()
    {
        service.delete("testid");
        Assertions.assertFalse(service.isExistEntity("testid"));
    }
    @Test
    @Order(6)
    void FindOperation_EntityIsNull_Return_EntityNotFoundException()
    {
        boolean isException = false;
        try
        {
            service.find("null");
        }
        catch (EntityNotFoundException e)
        {
            isException = true;
        }
        Assertions.assertTrue(isException);
    }



}
