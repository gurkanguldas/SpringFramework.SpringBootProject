package com.gurkanguldas.creditservice.business.service.factories;

import com.gurkanguldas.creditservice.business.dto.CreditDto;
import com.gurkanguldas.creditservice.business.service.impl.CreditService;
import com.gurkanguldas.creditservice.data.Credit;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
//If the person has more than one credit application, it deletes the previous credit applications.
public class CreditListSizeMoreThanOne implements IJustOneCredit{

    private List<Credit> creditList;
    private CreditService service;

    @Override
    public CreditDto justOne()
    {
        int size = creditList.size();

        for (int i=size-2 ; i>=0 ; i--)
            service.delete(creditList.get(i).getCreditId());

        return service.entitytoDto(creditList.get(size - 1));
    }
}
