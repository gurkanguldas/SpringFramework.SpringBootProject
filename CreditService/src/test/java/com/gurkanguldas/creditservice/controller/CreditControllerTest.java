package com.gurkanguldas.creditservice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gurkanguldas.creditservice.business.dto.CreditDto;
import com.gurkanguldas.creditservice.business.service.impl.CreditService;
import com.gurkanguldas.creditservice.exception.EntityNotFoundException;
import com.gurkanguldas.creditservice.exception.GenericContollerException;
import com.gurkanguldas.creditservice.rest.CreditRestController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
public class CreditControllerTest
{
    @Mock
    private CreditService service;

    @InjectMocks
    private CreditRestController personRestController;

    MockMvc mvc;
    @BeforeEach
    void beforeEachMethot()
    {
        JacksonTester.initFields(this, new ObjectMapper());
        mvc = MockMvcBuilders.standaloneSetup(personRestController).setControllerAdvice(new GenericContollerException()).build();
    }

    @Test
    void FindAllUrlOperation_GetStatus_OK() throws Exception
    {
        List<CreditDto> expectedPersons = getTestPersons();
        Mockito.when(service.findAll()).thenReturn(expectedPersons);

        MockHttpServletResponse response = mvc
                .perform(get("/rest/credit/get/credits").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse();
        List<CreditDto> actualPersons = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<CreditDto>>() {});

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertEquals(expectedPersons.size(),actualPersons.size());
    }
    @Test
    void FindAllUrlOperation_Throw_EntityNotFoundException_GetStatus_NOT_FOUND() throws Exception
    {
        Mockito.when(service.findAll()).thenThrow(EntityNotFoundException.class);

        MockHttpServletResponse response = mvc
                .perform(get("/rest/credit/get/credits").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
    @Test
    void FindUrlOperation_GetStatus_OK() throws Exception
    {
        List<CreditDto> expectedPersons = getTestPersons();
        Mockito.when(service.find("testid1")).thenReturn(expectedPersons.get(0));

        MockHttpServletResponse response = mvc
                .perform(get("/rest/credit/get?id=testid1").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse();
        CreditDto actualPersons = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<CreditDto>() {});

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertEquals(expectedPersons.get(0),actualPersons);
    }

    @Test
    void FindUrlOperation_Throw_EntityNotFoundException_GetStatus_NOT_FOUND() throws Exception
    {
        Mockito.when(service.find("testid1")).thenThrow(EntityNotFoundException.class);

        MockHttpServletResponse response = mvc
                .perform(get("/rest/credit/get?id=testid1").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void FindPersonIdentityUrlOperation_GetStatus_OK() throws Exception
    {
        List<CreditDto> expectedPersons = getTestPersons();
        Mockito.when(service.findByPersonIdentificationNumber(12345612345L)).thenReturn(expectedPersons.get(0));

        MockHttpServletResponse response = mvc
                .perform(get("/rest/credit/get/identity?id=12345612345").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse();
        CreditDto actualPersons = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<CreditDto>() {});

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertEquals(expectedPersons.get(0),actualPersons);
    }

    @Test
    void FindPersonIdentityUrlOperation_Throw_EntityNotFoundException_GetStatus_NOT_FOUND() throws Exception
    {
        Mockito.when(service.findByPersonIdentificationNumber(12345612345L)).thenThrow(EntityNotFoundException.class);

        MockHttpServletResponse response = mvc
                .perform(get("/rest/credit/get/identity?id=12345612345").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
    @Test
    void DeleteUrlOperation_GetStatus_OK() throws Exception
    {
        Mockito.doNothing().when(service).delete("testid1");

        MockHttpServletResponse response = mvc
                .perform(delete("/rest/credit/delete?id=testid1").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
    @Test
    void DeleteUrlOperation_Throw_EntityNotFoundException_GetStatus_NOT_FOUND() throws Exception
    {
        Mockito.doThrow(EntityNotFoundException.class).when(service).delete("testid1");

        MockHttpServletResponse response = mvc
                .perform(delete("/rest/credit/delete?id=testid1").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }

    private List<CreditDto> getTestPersons()
    {
        List<CreditDto> persons = new ArrayList<>();
        persons.add(new CreditDto("testid1",12346512365l,50000l,true));
        persons.add(new CreditDto("testid2",62136512365l,75000l,true));
        return persons;
    }
}
