package com.gurkanguldas.personsevice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gurkanguldas.personsevice.business.dto.PersonDto;
import com.gurkanguldas.personsevice.business.service.impl.PersonService;
import com.gurkanguldas.personsevice.exception.EntityNotFoundException;
import com.gurkanguldas.personsevice.exception.GenericContollerException;
import com.gurkanguldas.personsevice.rest.PersonRestController;
import org.junit.jupiter.api.*;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest
{
    @Mock
    private PersonService service;

    @InjectMocks
    private PersonRestController personRestController;

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
        List<PersonDto> expectedPersons = getTestPersons();
        Mockito.when(service.findAll()).thenReturn(expectedPersons);

        MockHttpServletResponse response = mvc
                .perform(get("/rest/person/get/people").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse();
        List<PersonDto> actualPersons = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<List<PersonDto>>() {});

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertEquals(expectedPersons.size(),actualPersons.size());
    }
    @Test
    void FindAllUrlOperation_Throw_EntityNotFoundException_GetStatus_NOT_FOUND() throws Exception
    {
        Mockito.when(service.findAll()).thenThrow(EntityNotFoundException.class);

        MockHttpServletResponse response = mvc
                .perform(get("/rest/person/get/people").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
    @Test
    void FindUrlOperation_GetStatus_OK() throws Exception
    {
        List<PersonDto> expectedPersons = getTestPersons();
        Mockito.when(service.find(1L)).thenReturn(expectedPersons.get(0));

        MockHttpServletResponse response = mvc
                .perform(get("/rest/person/get?id=1").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse();
        PersonDto actualPersons = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<PersonDto>() {});

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertEquals(expectedPersons.get(0),actualPersons);
    }

    @Test
    void FindUrlOperation_Throw_EntityNotFoundException_GetStatus_NOT_FOUND() throws Exception
    {
        Mockito.when(service.find(1L)).thenThrow(EntityNotFoundException.class);

        MockHttpServletResponse response = mvc
                .perform(get("/rest/person/get?id=1").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void FindPersonIdentityUrlOperation_GetStatus_OK() throws Exception
    {
        List<PersonDto> expectedPersons = getTestPersons();
        Mockito.when(service.findByPersonIdentificationNumber(12345612345L)).thenReturn(expectedPersons.get(0));

        MockHttpServletResponse response = mvc
                .perform(get("/rest/person/get/identity?id=12345612345").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse();
        PersonDto actualPersons = new ObjectMapper().readValue(response.getContentAsString(), new TypeReference<PersonDto>() {});

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        Assertions.assertEquals(expectedPersons.get(0),actualPersons);
    }

    @Test
    void FindPersonIdentityUrlOperation_Throw_EntityNotFoundException_GetStatus_NOT_FOUND() throws Exception
    {
        Mockito.when(service.findByPersonIdentificationNumber(12345612345L)).thenThrow(EntityNotFoundException.class);

        MockHttpServletResponse response = mvc
                .perform(get("/rest/person/get/identity?id=12345612345").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
    @Test
    void DeleteUrlOperation_GetStatus_OK() throws Exception
    {
        Mockito.doNothing().when(service).delete(1L);

        MockHttpServletResponse response = mvc
                .perform(delete("/rest/person/delete?id=1").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
    @Test
    void DeleteUrlOperation_Throw_EntityNotFoundException_GetStatus_NOT_FOUND() throws Exception
    {
        Mockito.doThrow(EntityNotFoundException.class).when(service).delete(1L);

        MockHttpServletResponse response = mvc
                .perform(delete("/rest/person/delete?id=1").accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print()).andReturn()
                .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }

    private List<PersonDto> getTestPersons()
    {
        List<PersonDto> persons = new ArrayList<>();
        persons.add(new PersonDto("Gurkan","Guldas",1L,12345612345L,5000L,5443443434L,1000L));
        persons.add(new PersonDto("Bekir","Guldas",2L,65432112345L,10000L,5344434343L,500L));
        return persons;
    }
}
