package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import com.SafetyNetAlert.SafetyNet.model.DataJson;
import com.SafetyNetAlert.SafetyNet.model.Person;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class PersonServiceImplTest {
    @Mock
    File file;
    @Mock
    JsonFileService jsonFileService;
    @Mock
    DataJson data;
    @InjectMocks
    PersonServiceImpl personServiceImpl;

    Person person = new Person();


    PersonServiceImplTest() throws IOException {
    }


    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        // Add info to parameter Person;
        person.setLastName("Scott");
        person.setFirstName("Michael");
        person.setPhone("555-6935-9866");
        person.setZip("57787");
        person.setAddress("78 3rd Street");
        person.setCity("Scranton");
        person.setEmail("MichaelScott@Dunder&Mifflin");

    }

    @Test
    void testCreatePerson() throws JSONException, IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<Person> result = personServiceImpl.createPerson(person);
        result.add(person);
        Assertions.assertEquals(Collections.singletonList(person), result);
    }

    @Test
    void testGetAllPerson() throws IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);
        List<Person> result = personServiceImpl.getAllPerson();
        result.add(person);
        Assertions.assertEquals(Collections.singletonList(person), result);
    }

    @Test
    void testUpdatePerson() throws IOException, JSONException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<Person> result = personServiceImpl.updatePerson(person);
        result.add(person);
        Assertions.assertEquals(Collections.singletonList(person), result);
    }

    @Test
    void testDeletePerson() throws IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<Person> result = personServiceImpl.deletePerson(person);
        result.add(person);
        Assertions.assertEquals(Collections.singletonList(person), result);
    }
}
