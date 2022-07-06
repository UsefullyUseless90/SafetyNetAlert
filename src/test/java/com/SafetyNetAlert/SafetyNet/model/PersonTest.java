package com.SafetyNetAlert.SafetyNet.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PersonTest {

    @Test
    void testConstructor() {

        Person actualPerson = new Person();
        actualPerson.setAddress("42 Main St");
        actualPerson.setCity("Oxford");
        actualPerson.setEmail("jane.doe@example.org");
        actualPerson.setFirstName("Jane");
        actualPerson.setLastName("Doe");
        actualPerson.setPhone("4105551212");
        actualPerson.setZip("21654");
        assertEquals("42 Main St", actualPerson.getAddress());
        assertEquals("Oxford", actualPerson.getCity());
        assertEquals("jane.doe@example.org", actualPerson.getEmail());
        assertEquals("Jane", actualPerson.getFirstName());
        assertEquals("Doe", actualPerson.getLastName());
        assertEquals("4105551212", actualPerson.getPhone());
        assertEquals("21654", actualPerson.getZip());
    }
}

