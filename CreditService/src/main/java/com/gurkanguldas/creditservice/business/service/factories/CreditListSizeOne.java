package com.gurkanguldas.creditservice.business.service.factories;

import com.gurkanguldas.creditservice.business.dto.CreditDto;
import com.gurkanguldas.creditservice.business.service.impl.CreditService;
import com.gurkanguldas.creditservice.data.Credit;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
//If the person has only one credit application, the person makes the credit application.
public class CreditListSizeOne implements IJustOneCredit{

    private List<Credit> creditList;
    private CreditService service;

    @Override
    public CreditDto justOne()
    {
        return service.entitytoDto(creditList.get(0));
    }
}
