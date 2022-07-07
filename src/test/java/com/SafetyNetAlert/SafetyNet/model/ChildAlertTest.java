package com.SafetyNetAlert.SafetyNet.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ChildAlertTest {

    ChildAlert cA = new ChildAlert();
    Person p = new Person();
    V2Person v2 = new V2Person(p);

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        p.setLastName("Scott");
        p.setFirstName("Michael");
        p.setPhone("4105551212");
        p.setAddress("78 3rd Street");
        p.setEmail("blahblah@blegh.com");

        v2.setLastName(p.getLastName());
        v2.setFirstName(p.getFirstName());
        v2.setAge(7);
        v2.setPhone(p.getPhone());
        v2.setEmail(p.getEmail());

        cA.setFirstName(v2.getFirstName());
        cA.setLastName(v2.getLastName());
        cA.setAge(v2.getAge());
        cA.setAddress(p.getAddress());

    }

    @Test
    void testConstructor() {

        assertEquals("78 3rd Street", cA.getAddress());
        assertEquals("Scott", cA.getLastName());
        assertEquals("Michael", cA.getFirstName());
        assertEquals(7, cA.getAge());

    }


}

