package com.SafetyNetAlert.SafetyNet.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CoveragePersonTest {

    @Test
    void testConstructor() {

        CoveragePerson actualCoveragePerson = new CoveragePerson(new V2Person(new Person()), "42 Main St");

        assertEquals("42 Main St", actualCoveragePerson.getAddress());
        assertNull(actualCoveragePerson.getPhoneNumber());
        assertNull(actualCoveragePerson.getLastName());
        assertNull(actualCoveragePerson.getFirstName());
    }

}

