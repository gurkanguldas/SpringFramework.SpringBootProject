package com.gurkanguldas.frontendservice.controller;

import com.gurkanguldas.frontendservice.dto.CreditDto;
import com.gurkanguldas.frontendservice.dto.EntityHost;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Controller
@RequestMapping("credit")
public class CreditController
{
    @GetMapping("/status")
    public String creditStatus(Model model)
    {
        model.addAttribute("key_identity",new CreditDto());
        return "PersonUpdateDeleteAndCreditStatusPage";
    }

    @PostMapping ("/status")
    public String creditStatus(@ModelAttribute("key_identity") CreditDto creditDto, Model model)
    {
        String URL = EntityHost.CreditHost.getHost()+"/rest/credit/get/identity?id="+creditDto.getPersonIdentificationNumber();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CreditDto> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, CreditDto.class);
        model.addAttribute("key_credit",responseEntity.getBody());
        return "CreditStatus";
    }
}
