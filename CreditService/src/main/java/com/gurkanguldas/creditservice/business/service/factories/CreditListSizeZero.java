package com.gurkanguldas.creditservice.business.service.factories;

import com.gurkanguldas.creditservice.business.dto.CreditDto;
import com.gurkanguldas.creditservice.exception.EntityNotFoundException;

//Throws EntityNotFoundException if the person has no credit request.
public class CreditListSizeZero implements IJustOneCredit{
    @Override
    public CreditDto justOne()
    {
        throw new EntityNotFoundException("Credit");
    }
}
