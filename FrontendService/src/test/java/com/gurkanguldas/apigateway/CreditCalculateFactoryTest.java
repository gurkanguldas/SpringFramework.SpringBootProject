package com.gurkanguldas.frontendservice;

import com.gurkanguldas.frontendservice.dto.CreditDto;
import com.gurkanguldas.frontendservice.dto.PersonDto;
import com.gurkanguldas.frontendservice.service.factories.CreditCalculateFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CreditCalculateFactoryTest
{
    private static PersonDto person;
    @BeforeAll
    static void beforeAllMethod()
    {
         person= PersonDto.builder()
                .personIdentificationNumber(12345612345L)
                .personMonthlyIncome(5000L)
                .personPhoneNumber(5443443434L)
                .build();
    }
    @Test
    void CreditScore_LessThan500_CreditStatus_False_CreditLimit_Zero()
    {
        long creditScore = 100;
        person.setPersonCredicScore(creditScore);

        CreditDto creditDto = CreditCalculateFactory.getCredit(person).calculate();

        Assertions.assertEquals(creditDto.getCreditLimit() , 0);
        Assertions.assertEquals(creditDto.getCreditStatus() , false);
    }
    @Test
    void CreditScore_Between500And1000_And_MontlyIncome_LessThan5000_CreditStatus_True_CreditLimit_10000()
    {
        long creditScore = 500;
        long monthlyIncome = 4500;

        person.setPersonCredicScore(creditScore);
        person.setPersonMonthlyIncome(monthlyIncome);

        CreditDto creditDto = CreditCalculateFactory.getCredit(person).calculate();

        Assertions.assertEquals(creditDto.getCreditLimit() , 10000);
        Assertions.assertEquals(creditDto.getCreditStatus() , true);
    }
    @Test
    void CreditScore_Between500And1000_And_MontlyIncome_GreaterThan5000_CreditStatus_True_CreditLimit_20000()
    {

        long creditScore = 750;
        long monthlyIncome = 5500;

        person.setPersonCredicScore(creditScore);
        person.setPersonMonthlyIncome(monthlyIncome);

        CreditDto creditDto = CreditCalculateFactory.getCredit(person).calculate();

        Assertions.assertEquals(creditDto.getCreditLimit() , 20000);
        Assertions.assertEquals(creditDto.getCreditStatus() , true);
    }
    @Test
    void CreditScore_GreaterThan1000_CreditStatus_True_CreditLimit_MonthlyIncomeCreditLimitMultiplier()
    {
        long creditScore = 1000;
        long monthlyIncome = 3500;

        person.setPersonCredicScore(creditScore);
        person.setPersonMonthlyIncome(monthlyIncome);

        CreditDto creditDto = CreditCalculateFactory.getCredit(person).calculate();

        Assertions.assertEquals(creditDto.getCreditLimit() , monthlyIncome * 4);
        Assertions.assertEquals(creditDto.getCreditStatus() , true);
    }

}
