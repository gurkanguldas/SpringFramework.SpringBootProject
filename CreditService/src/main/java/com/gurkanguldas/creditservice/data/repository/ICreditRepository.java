package com.gurkanguldas.creditservice.data.repository;

import com.gurkanguldas.creditservice.data.Credit;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
public interface ICreditRepository extends MongoRepository<Credit,String>
{
    List<Credit> findCreditsByPersonIdentificationNumber(Long id);
}
