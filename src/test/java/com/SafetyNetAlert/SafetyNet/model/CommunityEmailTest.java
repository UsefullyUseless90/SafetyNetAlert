package com.SafetyNetAlert.SafetyNet.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

class CommunityEmailTest {

    Person p = new Person();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        p.setLastName("Scott");
        p.setFirstName("Michael");
        p.setPhone("4105551212");
        p.setAddress("78 3rd Street");
        p.setEmail("blahblah@blegh.com");

    }

    @Test
    void testConstructor() {
        assertEquals("blahblah@blegh.com", (new CommunityEmail(p)).getEmail());
    }
}

