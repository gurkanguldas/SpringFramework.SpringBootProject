package com.gurkanguldas.frontendservice.service.factories;

import com.gurkanguldas.frontendservice.dto.CreditDto;
import com.gurkanguldas.frontendservice.dto.PersonDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
//Credit status is denied.
public class CreditStatusDenied implements ICreditCalculate
{

    private PersonDto person;

    @Override
    public CreditDto calculate()
    {
        return CreditDto.builder()
                .personIdentificationNumber(person.getPersonIdentificationNumber())
                .creditLimit(0l)
                .creditStatus(false)
                .build();
    }
}
