package com.gurkanguldas.creditservice.rest;

import com.gurkanguldas.creditservice.business.dto.CreditDto;
import com.gurkanguldas.creditservice.business.service.impl.CreditService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/rest/credit")
public class CreditRestController
{
    @Autowired
    CreditService creditService;

    // http://localhost:8082/rest/credit/get/credits
    @GetMapping("/get/credits")
    public ResponseEntity<?>  getCredits()
    {
       return ResponseEntity.ok().body(creditService.findAll());
    }

    // http://localhost:8082/rest/credit/get?id=a1b2c3
    @GetMapping("/get")
    public ResponseEntity<?> getCredit(@RequestParam("id")String creditId)
    {
        return ResponseEntity.ok().body(creditService.find(creditId));
    }

    // http://localhost:8082/rest/credit/get/identity?id=12345612345
    @GetMapping("/get/identity")
    public ResponseEntity<?> getCreditIdentity(@RequestParam("id")Long personIdintity)
    {
        return ResponseEntity.ok().body(creditService.findByPersonIdentificationNumber(personIdintity));
    }

    // http://localhost:8082/rest/credit/save
    @PostMapping(value = "/save")
    public ResponseEntity<?> saveCredit(@Valid @RequestBody CreditDto creditDto, BindingResult bindingResult)
    {
        creditService.save(creditDto,bindingResult);
        return ResponseEntity.ok().build();
    }

    // http://localhost:8082/rest/credit/delete
    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteCredit(@RequestParam("id")String creditId)
    {
        creditService.delete(creditId);
        return ResponseEntity.ok().build();
    }
}
