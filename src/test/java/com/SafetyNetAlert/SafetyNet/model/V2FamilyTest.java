package com.SafetyNetAlert.SafetyNet.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class V2FamilyTest {

    V2Family v2 = new V2Family("42 Main St");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        List<V2Person> v2People  = new ArrayList<>();
        v2.setPersonList(v2People);
        v2.setChildren(2);
        v2.setAdults(2);
        v2.setHouseHoldMembers(4);



    }

    @Test
    void testConstructor() {
        V2Family actualV2Family = new V2Family("42 Main St");
        assertEquals("42 Main St", actualV2Family.getAddress());
        assertTrue(actualV2Family.getPersonList().isEmpty());
        assertEquals(4, v2.getHouseHoldMembers());
        assertEquals(2, v2.getChildren());
        assertEquals(2, v2.getAdults());
    }

    @Test
    void testAddPerson(){

        V2Family v2Family = new V2Family("42 Main St");

        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");
        v2Family.addPerson(person);
    }

    @Test
    void testCalculateAge() {

        Person person = new Person();
        person.setAddress("42 Main St");
        person.setCity("Oxford");
        person.setEmail("jane.doe@example.org");
        person.setFirstName("Jane");
        person.setLastName("Doe");
        person.setPhone("4105551212");
        person.setZip("21654");
        v2.calculateAge();
        assertEquals("42 Main St", person.getAddress());
        assertEquals("21654", person.getZip());
        assertEquals("4105551212", person.getPhone());
        assertEquals("Doe", person.getLastName());
        assertEquals("Jane", person.getFirstName());
        assertEquals("jane.doe@example.org", person.getEmail());
        assertEquals("Oxford", person.getCity());
        assertEquals("42 Main St", v2.getAddress());
        assertTrue(v2.getPersonList().isEmpty());
        assertEquals(4, v2.getHouseHoldMembers());
        assertEquals(2, v2.getChildren());
        assertEquals(2, v2.getAdults());
    }
}

