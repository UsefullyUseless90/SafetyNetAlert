package com.SafetyNetAlert.SafetyNet.controller;

import static org.mockito.Mockito.when;

import com.SafetyNetAlert.SafetyNet.model.Person;
import com.SafetyNetAlert.SafetyNet.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {PersonController.class})
@ExtendWith(SpringExtension.class)
class PersonControllerTest {
    @Autowired
    private PersonController personController;

    @MockBean
    private PersonService personService;

    @Test
    void testCreatePerson() throws Exception {
       // when(this.personService.getAllPerson()).thenReturn(new ArrayList<>());

        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");
        String content = (new ObjectMapper()).writeValueAsString(person);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAllPerson() throws Exception {
       // when(this.personService.getAllPerson()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person");
        MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testDeletePerson() throws Exception {
       // when(this.personService.getAllPerson()).thenReturn(new ArrayList<>());

        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");
        String content = (new ObjectMapper()).writeValueAsString(person);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testUpdatePerson() throws Exception {
      //  when(this.personService.getAllPerson()).thenReturn(new ArrayList<>());

        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");
        String content = (new ObjectMapper()).writeValueAsString(person);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.personController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

