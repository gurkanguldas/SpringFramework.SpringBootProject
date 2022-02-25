package com.gurkanguldas.creditservice.business.service.factories;


import com.gurkanguldas.creditservice.business.service.impl.CreditService;
import com.gurkanguldas.creditservice.data.Credit;

import java.util.List;

public class JustOneCreditFactory
{
    public static IJustOneCredit listTo(List<Credit> creditList, CreditService service)
    {
        int size = creditList.size();

        if(size == 0)
            return new CreditListSizeZero();
        else if(size > 1)
            return new CreditListSizeMoreThanOne(creditList,service);
        else
            return new CreditListSizeOne(creditList,service);
    }
}
