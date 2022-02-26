package com.gurkanguldas.frontendservice.controller;

import com.gurkanguldas.frontendservice.dto.CreditDto;
import com.gurkanguldas.frontendservice.dto.EntityHost;
import com.gurkanguldas.frontendservice.dto.PersonDto;
import com.gurkanguldas.frontendservice.service.factories.CreditCalculateFactory;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@Log4j2
@Controller
@RequestMapping("/person")
public class PersonController
{
    // http://localhost:8080/person/create
    @GetMapping(value = "/create")
    public String createPerson(Model model)
    {
        model.addAttribute("key_person", new PersonDto());
        return "PersonRegisterAndLoginPage";
    }

    // http://localhost:8080/person/create
    @PostMapping(value = "/create")
    public String createPerson( Model model, @Valid @ModelAttribute("key_person") PersonDto personDto , BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
            return "PersonRegisterAndLoginPage";

        model.addAttribute("key_credit", CreditCalculateFactory.getCredit(personDto).calculate());
        return "PersonsCreditStatusPage";
    }

    // http://localhost:8080/person/update?personIdentificationNumber=13246512345
    @PostMapping(value = "/update")
    public String updatePerson( Model model, @RequestParam("personIdentificationNumber") Long id)
    {
        String URL = EntityHost.PersonHost.getHost()+"/rest/person/get/identity?id="+id;

        RestTemplate restTemplate = new RestTemplate();
        PersonDto personDto = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, PersonDto.class).getBody();

        model.addAttribute("key_person",personDto);
        return "PersonRegisterAndLoginPage";
    }

    // http://localhost:8080/person/save
    @PostMapping("/save")
    public String savePersonAndCredit(@ModelAttribute("key_person") PersonDto personDto,
                                      @ModelAttribute("key_credit") CreditDto creditDto)
    {
        String savePersonURL = EntityHost.PersonHost.getHost()+"/rest/person/save";
        String saveCreditURL = EntityHost.CreditHost.getHost()+"/rest/credit/save";

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.exchange(savePersonURL, HttpMethod.POST, new HttpEntity<>(personDto), PersonDto.class);
        restTemplate.exchange(saveCreditURL, HttpMethod.POST, new HttpEntity<>(creditDto), CreditDto.class);

        log.info("SMS sent to "+personDto.getPersonPhoneNumber()+". Credit Status: "+creditDto.getCreditStatus()+". Credit Limit: "+creditDto.getCreditLimit());

        return "PersonRegisterAndLoginPage";
    }

    // http://localhost:8080/person/delete?personIdentificationNumber=12345612345
    @DeleteMapping("/delete")
    public String deletePersonAndCredit(@RequestParam("personIdentificationNumber") Long id, Model model)
    {
        model.addAttribute("key_person", new PersonDto());

        String findPersonURL = EntityHost.PersonHost.getHost()+"/rest/person/get/identity?id="+id;
        String findCreditURL = EntityHost.CreditHost.getHost()+"/rest/credit/get/identity?id="+id;

        String deletePersonURL = EntityHost.PersonHost.getHost()+"/rest/person/delete?id=";
        String deleteCreditURL = EntityHost.CreditHost.getHost()+"/rest/credit/delete?id=";

        RestTemplate restTemplate = new RestTemplate();

        PersonDto personDto = restTemplate.exchange(findPersonURL, HttpMethod.GET, HttpEntity.EMPTY, PersonDto.class).getBody();
        CreditDto creditDto = restTemplate.exchange(findCreditURL, HttpMethod.GET, HttpEntity.EMPTY, CreditDto.class).getBody();

        restTemplate.exchange(deletePersonURL+personDto.getPersonId(),HttpMethod.DELETE, HttpEntity.EMPTY, PersonDto.class);
        restTemplate.exchange(deleteCreditURL+creditDto.getCreditId(),HttpMethod.DELETE, HttpEntity.EMPTY, CreditDto.class);


        return "PersonRegisterAndLoginPage";
    }
}
