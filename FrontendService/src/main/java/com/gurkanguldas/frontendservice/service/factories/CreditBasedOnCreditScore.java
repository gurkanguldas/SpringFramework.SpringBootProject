package com.gurkanguldas.frontendservice.service.factories;

import com.gurkanguldas.frontendservice.dto.CreditDto;
import com.gurkanguldas.frontendservice.dto.PersonDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
//Credit status is calculated based on credit score.
public class CreditBasedOnCreditScore implements ICreditCalculate{

    private PersonDto person;
    @Override
    public CreditDto calculate()
    {
        return CreditDto.builder()
                .personIdentificationNumber(person.getPersonIdentificationNumber())
                .creditLimit(person.getPersonMonthlyIncome() < 5000 ? 10000L : 20000L)
                .creditStatus(true)
                .build();
    }
}
