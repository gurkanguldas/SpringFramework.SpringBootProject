package com.gurkanguldas.frontendservice.service.factories;

import com.gurkanguldas.frontendservice.dto.CreditDto;
import com.gurkanguldas.frontendservice.dto.PersonDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
//Credit status is calculated based on monthly income.
public class CreditBasedOnMonthlyIncome implements ICreditCalculate{

    private PersonDto person;

    @Override
    public CreditDto calculate()
    {
        int creditLimitMultiplier = 4;

        return CreditDto.builder()
                .personIdentificationNumber(person.getPersonIdentificationNumber())
                .creditLimit(person.getPersonMonthlyIncome() * creditLimitMultiplier)
                .creditStatus(true)
                .build();
    }
}
