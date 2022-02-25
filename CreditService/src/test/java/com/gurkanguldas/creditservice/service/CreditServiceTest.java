package com.gurkanguldas.creditservice.service;

import com.gurkanguldas.creditservice.business.dto.CreditDto;
import com.gurkanguldas.creditservice.business.service.impl.CreditService;
import com.gurkanguldas.creditservice.exception.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindingResult;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreditServiceTest
{
    @Mock
    BindingResult bindingResult;

    @Autowired
    CreditService service;
    @Test
    void FindPersonIdentificationOperation_OnePerson_OnlyGetOneCredit_Success()
    {
        int saveNum = 3;
        MockitoAnnotations.openMocks(this);
        CreditDto creditDto = CreditDto.builder().personIdentificationNumber(97693724104L).creditStatus(true).build();

        for(int i=0; i<saveNum; i++)
        {
            creditDto.setCreditLimit(50000L);
            service.save(creditDto,bindingResult);
        }

        int before = service.findAll().size();

        creditDto = service.findByPersonIdentificationNumber(97693724104L);
        int after = service.findAll().size();

        Assertions.assertEquals(before , after + saveNum - 1);

        service.delete(creditDto.getCreditId());
    }
    @Test
    void FindPersonIdentificationOperation_Null_Throw_EntityNotFoundException()
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
