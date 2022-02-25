package com.gurkanguldas.frontendservice.service.factories;

import com.gurkanguldas.frontendservice.dto.PersonDto;

public class CreditCalculateFactory
{
    public static ICreditCalculate getCredit(PersonDto person)
    {
        long credicScore = person.getPersonCredicScore();

        if(credicScore < 500)
            return new CreditStatusDenied(person);

        else if(credicScore >= 500 && credicScore < 1000)
            return new CreditBasedOnCreditScore(person);

        else
            return new CreditBasedOnMonthlyIncome(person);
    }
}
