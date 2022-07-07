package com.SafetyNetAlert.SafetyNet.service;

import com.SafetyNetAlert.SafetyNet.jsonfiles.JsonFileService;
import com.SafetyNetAlert.SafetyNet.model.DataJson;
import com.SafetyNetAlert.SafetyNet.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
class PersonServiceImplTest {

    @Mock
    JsonFileService jsonFileService;
    @Mock
    DataJson data;
    @InjectMocks
    PersonServiceImpl personServiceImpl;

    Person person = new Person();

    @BeforeEach
    void setUp() {
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
    void testCreatePerson() throws IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<Person> result = personServiceImpl.createPerson(person);
        result.add(person);
        Assertions.assertEquals(Collections.singletonList(person), result);
    }

    @Test
    void testCreatePersonInList() throws IOException {
        DataJson dataJson = new DataJson();
        ArrayList<Person> personList = new ArrayList<>();
        dataJson.setPersons(personList);
        doNothing().when(this.jsonFileService).updatePersons((List<Person>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");
        List<Person> actualCreatePersonResult = this.personServiceImpl.createPerson(person);
        assertSame(personList, actualCreatePersonResult);
        assertEquals(1, actualCreatePersonResult.size());
        verify(this.jsonFileService, atLeast(1)).jsonReaderService();
        verify(this.jsonFileService).updatePersons((List<Person>) any());
        assertEquals("42 Main St", person.getAddress());
        assertEquals("21654", person.getZip());
        assertEquals("4105551212", person.getPhone());
        assertEquals("Doe", person.getLastName());
        assertEquals("Jane", person.getFirstName());
        assertEquals("jane.doe@example.org", person.getEmail());
        assertEquals("Oxford", person.getCity());
    }

    @Test
    void testCreatePersonExceptionsThrowable() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setPersons(new ArrayList<>());
        doThrow(new IOException("An error occurred")).when(this.jsonFileService).updatePersons((List<Person>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");
        assertThrows(IOException.class, () -> this.personServiceImpl.createPerson(person));
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updatePersons((List<Person>) any());
    }

    @Test
    void testGetAllPerson() throws IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);
        List<Person> result = personServiceImpl.getAllPerson();
        result.add(person);
        Assertions.assertEquals(Collections.singletonList(person), result);
    }

    @Test
    void testGetAllPersonVerifyList() throws IOException {
        DataJson dataJson = new DataJson();
        ArrayList<Person> personList = new ArrayList<>();
        dataJson.setPersons(personList);
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);
        List<Person> actualAllPerson = this.personServiceImpl.getAllPerson();
        assertSame(personList, actualAllPerson);
        assertTrue(actualAllPerson.isEmpty());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testGetAllPersonFailed() throws IOException {
        when(this.jsonFileService.jsonReaderService()).thenThrow(new IOException("An error occurred"));
        assertThrows(IOException.class, () -> this.personServiceImpl.getAllPerson());
        verify(this.jsonFileService).jsonReaderService();
    }

    @Test
    void testUpdatePerson() throws IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<Person> result = personServiceImpl.updatePerson(person);
        result.add(person);
        Assertions.assertEquals(Collections.singletonList(person), result);
    }

    @Test
    void testUpdatePersonVerify() throws IOException {
        DataJson dataJson = new DataJson();
        ArrayList<Person> personList = new ArrayList<>();
        dataJson.setPersons(personList);
        doNothing().when(this.jsonFileService).updatePersons((List<Person>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");
        List<Person> actualUpdatePersonResult = this.personServiceImpl.updatePerson(person);
        assertSame(personList, actualUpdatePersonResult);
        assertTrue(actualUpdatePersonResult.isEmpty());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updatePersons((List<Person>) any());
    }

    @Test
    void testUpdatePersonThrowableException() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setPersons(new ArrayList<>());
        doThrow(new IOException("An error occurred")).when(this.jsonFileService).updatePersons((List<Person>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");
        assertThrows(IOException.class, () -> this.personServiceImpl.updatePerson(person));
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updatePersons((List<Person>) any());
    }

    @Test
    void testUpdatePersonMultipleInList() throws IOException {
        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);

        DataJson dataJson = new DataJson();
        dataJson.setPersons(personList);
        doNothing().when(this.jsonFileService).updatePersons((List<Person>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        Person person1 = new Person();
        person1.setAddress("42 Main St");
        person1.setCity("Oxford");
        person1.setEmail("jane.doe@example.org");
        person1.setFirstName("Jane");
        person1.setLastName("Doe");
        person1.setPhone("4105551212");
        person1.setZip("21654");
        List<Person> actualUpdatePersonResult = this.personServiceImpl.updatePerson(person1);
        assertSame(personList, actualUpdatePersonResult);
        assertEquals(1, actualUpdatePersonResult.size());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updatePersons((List<Person>) any());
    }

    @Test
    void testDeletePerson() throws IOException {
        when(jsonFileService.jsonReaderService()).thenReturn(data);

        List<Person> result = personServiceImpl.deletePerson(person);
        result.add(person);
        Assertions.assertEquals(Collections.singletonList(person), result);
    }

    @Test
    void testDeletePersonVerifyList() throws IOException {
        DataJson dataJson = new DataJson();
        ArrayList<Person> personList = new ArrayList<>();
        dataJson.setPersons(personList);
        doNothing().when(this.jsonFileService).updatePersons((List<Person>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");
        List<Person> actualDeletePersonResult = this.personServiceImpl.deletePerson(person);
        assertSame(personList, actualDeletePersonResult);
        assertTrue(actualDeletePersonResult.isEmpty());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updatePersons((List<Person>) any());
    }

    @Test
    void testDeletePersonThrowableException() throws IOException {
        DataJson dataJson = new DataJson();
        dataJson.setPersons(new ArrayList<>());
        doThrow(new IOException("An error occurred")).when(this.jsonFileService).updatePersons((List<Person>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");
        assertThrows(IOException.class, () -> this.personServiceImpl.deletePerson(person));
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updatePersons((List<Person>) any());
    }

    @Test
    void testDeletePersonMultiple() throws IOException {
        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");

        ArrayList<Person> personList = new ArrayList<>();
        personList.add(person);

        DataJson dataJson = new DataJson();
        dataJson.setFirestations(new ArrayList<>());
        dataJson.setMedicalrecords(new ArrayList<>());
        dataJson.setPersons(personList);
        doNothing().when(this.jsonFileService).updatePersons((List<Person>) any());
        when(this.jsonFileService.jsonReaderService()).thenReturn(dataJson);

        Person person1 = new Person();
        person1.setAddress("42 Main St");
        person1.setCity("Oxford");
        person1.setEmail("jane.doe@example.org");
        person1.setFirstName("Jane");
        person1.setLastName("Doe");
        person1.setPhone("4105551212");
        person1.setZip("21654");
        List<Person> actualDeletePersonResult = this.personServiceImpl.deletePerson(person1);
        assertSame(personList, actualDeletePersonResult);
        assertTrue(actualDeletePersonResult.isEmpty());
        verify(this.jsonFileService).jsonReaderService();
        verify(this.jsonFileService).updatePersons((List<Person>) any());
    }
}
