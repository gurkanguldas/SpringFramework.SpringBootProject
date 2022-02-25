package com.gurkanguldas.creditservice.business.service.impl;

import com.gurkanguldas.creditservice.business.service.CrudBaseService;
import com.gurkanguldas.creditservice.business.service.factories.JustOneCreditFactory;
import com.gurkanguldas.creditservice.data.Credit;
import com.gurkanguldas.creditservice.business.dto.CreditDto;
import com.gurkanguldas.creditservice.data.repository.ICreditRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class CreditService extends CrudBaseService<Credit, CreditDto,String> {

    @Autowired
    ICreditRepository repository;

    public CreditService(ICreditRepository repository) {
        super(repository);
    }

    // Find by person idetity
    public CreditDto findByPersonIdentificationNumber(Long id)
    {
        List<Credit> creditDtoList = repository.findCreditsByPersonIdentificationNumber(id);
        return justOneCredit(creditDtoList);
    }

    //A person can only apply for one credit. The method checks this state.
    private CreditDto justOneCredit(List<Credit> creditDtoList)
    {
        return JustOneCreditFactory.listTo(creditDtoList,this).justOne();
    }


}
