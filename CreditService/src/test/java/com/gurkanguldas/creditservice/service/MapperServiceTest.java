package com.gurkanguldas.creditservice.service;

import com.gurkanguldas.creditservice.business.dto.CreditDto;
import com.gurkanguldas.creditservice.business.service.MapperService;
import com.gurkanguldas.creditservice.data.Credit;
import com.gurkanguldas.creditservice.exception.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MapperServiceTest
{

    @Autowired
    MapperService<Credit, CreditDto> service;

    @Test
    void EntityDto_Mapper_To_Entity_Success()
    {
        CreditDto creditDto = CreditDto.builder()
                .creditId("testid")
                .personIdentificationNumber(99693724104L)
                .creditLimit(50000l)
                .creditStatus(true)
                .build();

        Credit credit = service.dtoToEntity(creditDto);
        Assertions.assertEquals(credit.getPersonIdentificationNumber(),creditDto.getPersonIdentificationNumber());
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
        Credit credit = Credit.builder()
                .creditId("testid")
                .personIdentificationNumber(99693724104L)
                .creditLimit(50000l)
                .creditStatus(true)
                .build();

        CreditDto creditDto = service.entitytoDto(credit);
        Assertions.assertEquals(creditDto.getPersonIdentificationNumber(),credit.getPersonIdentificationNumber());
    }
    @Test
    void Entity_Null_Mapper_To_EntityDto_Throw_EntityNotFoundException()
    {
        boolean isException = false;
        try
        {
            service.entitytoDto(null);
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
        Assertions.assertEquals(strEntity,"Credit");
    }
}
